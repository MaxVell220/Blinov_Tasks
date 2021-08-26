<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
    <html>
      <body>
        <h1>newAgency travel</h1>
        <table border="1">
          <tr>
            <th>Type</th>
            <th>Country</th>
            <th>Days</th>
            <th>Transport</th>
            <th>Cost</th>
            <th>numberOfStarts</th>
            <th>isMealsIncluded</th>
            <th>Meals type</th>
            <th>number Of Rooms</th>
            <th>is Tv Included</th>
            <th>is Air Conditioning Included</th>

          </tr>
          <xsl:for-each select="agency/tourist_voucher">
            <tr>
              <td>
                <xsl:value-of select="type"/>
              </td>
              <td>
                <xsl:value-of select="country"/>
              </td>
              <td>
                <xsl:value-of select="days"/>
              </td>
              <td>
                <xsl:value-of select="transport"/>
              </td>
              <td>
                <xsl:value-of select="cost"/>
              </td>
              <td>
                <xsl:value-of select="hotel/numberOfStarts"/>
              </td>
              <td>
                <xsl:value-of select="hotel/isMealsIncluded"/>
              </td>
              <td>
                <xsl:value-of select="hotel/meals"/>
              </td>
              <td>
                <xsl:value-of select="hotel/numberOfRooms"/>
              </td>
              <td>
                <xsl:value-of select="hotel/isTvIncluded"/>
              </td>
              <td>
                <xsl:value-of select="hotel/isAirConditioningIncluded"/>
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>