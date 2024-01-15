https://copyprogramming.com/howto/apache-poi-xssfcolor-from-hex-code

  
////             Create a custom color  
//            Color customColor = new XSSFColor(new java.awt.Color(0xF2, 0x94, 0x23));  
//  
//            // Create a cell style with the custom color  
//            CellStyle cellStyle = workbook.createCellStyle();  
//            cellStyle.setFillForegroundColor(((XSSFColor) customColor).getIndex());  
//            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
//  
//            // Find the position of the Unicode character in the cell value  
//            int unicodePosition = cellValue.indexOf("\u26A0");  
//  
//            // Apply the custom cell style to the specific Unicode character  
//            if (unicodePosition >= 0) {  
//              RichTextString richText = new XSSFRichTextString(cellValue);  
//              richText.applyFont(unicodePosition, unicodePosition + 1, (short) cellStyle.getFontIndexAsInt());  
//              cell.setCellValue(richText);  
//            }  
  
            // Apply font to the exclamation triangle part            CellStyle cellStyle = workbook.createCellStyle();  
            Font font = workbook.createFont();  
            font.setColor(IndexedColors.LIGHT_ORANGE.getIndex());  
            font.setBold(true);  
            cellStyle.setFont(font);  
//            cell.getRichStringCellValue().applyFont(cellValue.indexOf('⚠'), cellValue.length(), font);  
//            String rgbS = "F29423";  
////            byte[] rgbB = Hex.decodeHex(rgbS); // get byte array from hex string  
//            byte[] rgb = new byte[3];  
//            rgb[0] = (byte) 242; // red  
//            rgb[1] = (byte) 220; // green  
//            rgb[2] = (byte) 219; // blue  
//            XSSFColor color = new XSSFColor(rgb);  
  
//            XSSFCellStyle cellStyle = workbook.createCellStyle();  
//            cellStyle.setFillForegroundColor(color);  
//            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
//            cell.setCellStyle(cellStyle);  
//             color = new XSSFColor((IndexedColorMap) java.awt.Color.decode("#F29423"));  
//            color = new XSSFColor((IndexedColorMap) new java.awt.Color(0xF2, 0x94, 0x23));  
  
  
//            HSSFPalette palette = workbook.getCustomPalette();  
// get the color which most closely matches the color you want to use  
//            HSSFColor myColor = palette.findSimilarColor(242, 148, 35);  
// get the palette index of that color  
//            short palIndex = myColor.getIndex();  
//// code to get the style for the cell goes here  
//            style.setFillForegroundColor(palIndex);  
  
//            HSSFCellStyle fontColorStyle = workbook.createCellStyle();  
//            Font font = workbook.createFont();  
//            font.setColor(palIndex);  
//            fontColorStyle.setFont(font);  
//            cell.setCellStyle(fontColorStyle);  
  
//             Find the position of the Unicode character in the cell value  
            int unicodePosition = cellValue.indexOf("▲");  
//             Apply the custom cell style to the specific Unicode character  
            if (unicodePosition >= 0) {  
              RichTextString richText = new XSSFRichTextString(cellValue);  
              richText.applyFont(unicodePosition, unicodePosition + 1, (short) cellStyle.getFontIndex());  
              cell.setCellValue(richText);  
            }  
//            cell.setCellStyle(cellStyle);

