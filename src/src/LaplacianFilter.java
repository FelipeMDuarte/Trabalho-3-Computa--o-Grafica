package src;


	import java.awt.image.BufferedImage;
import java.util.Calendar;

import util.ArrayData;

	public class LaplacianFilter  {
		private ImageToArrayData transformer;
		private ArrayData[] arrayData;
		
		public LaplacianFilter(BufferedImage image) {
			this.transformer =   new ImageToArrayData(image);
			this.arrayData =  transformer.getArrayData();
		}
		
		public BufferedImage LaplaceFilter() {
		// TODO Auto-generated method stub
	    ArrayData reds = arrayData[0];
	    ArrayData greens =arrayData[1];
	    ArrayData blues = arrayData[2];
	    BufferedImage outputImage = new BufferedImage(reds.width, reds.height,
	                                                  BufferedImage.TYPE_INT_ARGB);
	  
	    for (int y = 1; y < reds.height -1; y++)
	    {
	      for (int x = 1; x < reds.width -1 ; x++)
	      {	    	
		        int red = bound((8* reds.get(x, y) - reds.get(x-1, y-1)                 
		        		- reds.get(x, y - 1)
	                    - reds.get(x + 1, y - 1)
	                    - reds.get(x - 1, y) - reds.get(x, y)
	                    - reds.get(x + 1, y)
	                    - reds.get(x - 1, y + 1)
	                    - reds.get(x, y + 1)
	                    - reds.get(x + 1, y + 1)) , 256);
		        int green =bound((8* reds.get(x, y) - reds.get(x-1, y-1)                 
		        		- greens.get(x, y - 1)
	                    - greens.get(x + 1, y - 1)
	                    - greens.get(x - 1, y) - greens.get(x, y)
	                    - greens.get(x + 1, y)
	                    - greens.get(x - 1, y + 1)
	                    - greens.get(x, y + 1)
	                    - greens.get(x + 1, y + 1)) , 256);
		        int blue = bound((8* reds.get(x, y) - reds.get(x-1, y-1)                 
		        		- blues.get(x, y - 1)
	                    - blues.get(x + 1, y - 1)
	                    - blues.get(x - 1, y) - blues.get(x, y)
	                    - blues.get(x + 1, y)
	                    - blues.get(x - 1, y + 1)
	                    - blues.get(x, y + 1)
	                    - blues.get(x + 1, y + 1)) , 256);
		        
		        red = (int)((red + 8 * 255) / 16);
		        green = (int)( (green + 8 *255) /16);
		        blue = (int) ((blue +8 *255)/ 16);
	        outputImage.setRGB(x, y, (red << 16) | (green << 8) | blue | -0x01000000);
	      }
	    }
	
	    
	    return outputImage;
		
	}
		  private static int bound(int value, int endIndex)
		  {
		    if (value < 0)
		      return 0;
		    if (value < endIndex)
		      return value;
		    return endIndex - 1;
		  }
}


