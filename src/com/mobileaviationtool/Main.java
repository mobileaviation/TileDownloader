package com.mobileaviationtool;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // Check for tiles from zoom 6 - 12

        int zoom  = 10;
        double lat = 47.96805d;
        double lon = 7.909167d;

        Tile tt = new Tile();
        //tt.getTileNumber(lat, lon, zoom);

        //System.out.println("http://tile.openstreetmap.org/" + tt.z + "/" + tt.x + "/" + tt.y + ".png");

        BoundingBox bb = new BoundingBox();
        //bb.tile2boundingBox(tt.x, tt.y, tt.z);

        //System.out.println("BoundingBox North(lat)" + bb.north + " South(lat)" + bb.south + " West(lon)"  + bb.west + " East(lon)" + bb.east);

//        Coordinate[] testCoords = new Coordinate[5];
//        testCoords[0] = new Coordinate(3.36d, 50.73d);
//        testCoords[1] = new Coordinate(3.36d, 53.67d);
//        testCoords[2] = new Coordinate(7.18d, 53.67d);
//        testCoords[3] = new Coordinate(7.18d, 50.73d);
//        testCoords[4] = new Coordinate(3.36d, 50.73d);
//        Geometry testBox = new GeometryFactory().createPolygon(testCoords);

        TestBuffer testBuffer = new TestBuffer();
        Coordinate[] testCoords = testBuffer.envelope.getCoordinates();


        int tileCount = 0;
        for (int z = 6; z <= 13; z++) {
            tt.getTileNumber(testCoords[1].y, testCoords[0].x, z);
            //System.out.println("http://tile.openstreetmap.org/" + tt.z + "/" + tt.x + "/" + tt.y + ".png");
            int xBegin = tt.x;
            int yBegin = tt.y;
            tt.getTileNumber(testCoords[3].y, testCoords[2].x, z);
            //System.out.println("http://tile.openstreetmap.org/" + tt.z + "/" + tt.x + "/" + tt.y + ".png");
            int xEnd = tt.x;
            int yEnd = tt.y;

            for (int x = xBegin; x<=xEnd; x++)
            {
                for (int y = yBegin; y<=yEnd; y++)
                {
                    bb.tile2boundingBox(x,y,z);
                    if (testBuffer.buffer.intersects(bb.bbbox))
                    {
                        System.out.println("http://tile.openstreetmap.org/" + z + "/" + x + "/" + y + ".png");
                        tileCount++;
                    }
                    else
                    {
                        System.out.println(z + "/" + x + "/" + y + " is outside of buffer");
                    }
                }

            }

        }
        System.out.println("Total Tile Count: " + tileCount);
    }
}
