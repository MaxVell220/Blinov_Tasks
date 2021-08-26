import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.DeleteDimensionRequest;
import com.google.api.services.sheets.v4.model.DimensionRange;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SheetsAndJava {

  private static Sheets sheetsService;
  private static String APPLICATION_NAME = "Google Sheets API";
  private static String SPREADSHEET_ID = "1uN9ZtAcUJx5l8CVA1hOG7mKXaMinD1YWBX44yJ8NdmM";

  private static Credential authorize() throws IOException, GeneralSecurityException {
    InputStream in = SheetsAndJava.class.getResourceAsStream("/credentials.json");
    assert in != null;
    GoogleClientSecrets clientSecrets =
      GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
    List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
      GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
      clientSecrets, scopes
    ).setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
      .setAccessType("offline").build();
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

  public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
    Credential credential = authorize();
    return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
      JacksonFactory.getDefaultInstance(), credential).setApplicationName(APPLICATION_NAME).build();
  }

  public static void readSheet(Sheets sheetsService, String range) throws IOException {
    ValueRange response = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();

    List<List<Object>> values = response.getValues();

    if (values == null || values.isEmpty()) {
      System.out.println("No data found.");
    } else {
      for (List row : values) {
        System.out.printf("%s, %s from %s\n", row.get(5), row.get(4), row.get(1));
      }
    }
  }

  public static void createRow(Sheets sheetsService, List<Object> values) throws IOException {
    ValueRange appendBody = new ValueRange().setValues(Arrays.asList(values));

    AppendValuesResponse appendResult =
      sheetsService.spreadsheets().values().append(SPREADSHEET_ID, "House", appendBody)
        .setValueInputOption("USER_ENTERED")
        .setInsertDataOption("INSERT_ROWS").setIncludeValuesInResponse(true).execute();
  }

  public static void updateCell(Sheets sheetsService, String value, String placeToUpdate) throws IOException {
    ValueRange body = new ValueRange().setValues(Arrays.asList(Arrays.asList(value)));

    UpdateValuesResponse
      result = sheetsService.spreadsheets().values().update(SPREADSHEET_ID, placeToUpdate, body).setValueInputOption("RAW")
      .setIncludeValuesInResponse(true).execute();
  }

  public static void deleteCell(Sheets sheetsService, Integer sheetId,Integer startIndex) throws IOException {
    DeleteDimensionRequest deleteRequest = new DeleteDimensionRequest().
      setRange(
        new DimensionRange()
        .setSheetId(sheetId)
        .setDimension("ROWS")
        .setStartIndex(startIndex)
      );
    List<Request> requests = new ArrayList<>();
    requests.add(new Request().setDeleteDimension(deleteRequest));
    BatchUpdateSpreadsheetRequest body = new BatchUpdateSpreadsheetRequest().setRequests(requests);
    sheetsService.spreadsheets().batchUpdate(SPREADSHEET_ID, body).execute();
  }

  public static void main(String[] args) throws GeneralSecurityException, IOException {
    sheetsService = getSheetsService();
    String range = "House!A4:F10";
    readSheet(sheetsService, range);
    createRow(sheetsService, Arrays.asList("This", "is", "added", "from", "the", "code"));
    updateCell(sheetsService, "UPDATED", "House!C439");
    deleteCell(sheetsService, 1178631925, 439);
  }
}
