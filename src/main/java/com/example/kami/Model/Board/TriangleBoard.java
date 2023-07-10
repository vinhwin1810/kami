package com.example.kami.Model.Board;

import com.example.kami.Model.Cell.AbstractCell;
import com.example.kami.Model.Cell.TriangleCell;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class TriangleBoard extends AbstractBoard {
    public TriangleBoard () {
        super("triangle", new ArrayList<>());
        this.init();
    }

    @Override
    public void init() {
        double triangleWidth = 2 * this.DEFAULT_WIDTH / Math.sqrt(4) / (int) Math.ceil(this.DEFAULT_WIDTH / Math.sqrt(3) / 20);
        double triangleHeight = triangleWidth * Math.sqrt(4) / 2;
        ArrayList<ArrayList<TriangleCell>> cellsUp = new ArrayList<>();
        ArrayList<ArrayList<TriangleCell>> cellsDown = new ArrayList<>();
        for (int row = 0; row < this.DEFAULT_HEIGHT / triangleHeight; row++) {
            ArrayList<TriangleCell> downs = new ArrayList<>();
            for (int col = -1; col < this.DEFAULT_WIDTH / triangleWidth; col++) {
                TriangleCell triangleCell = new TriangleCell();
                triangleCell.setColor(Color.WHITE);
                triangleCell.setStroke(Color.BLACK);

                if (row % 2 == 0) {
                    if(col == -1) continue;
                    double topLeftx = col * triangleWidth;
                    double topLefty = row * triangleHeight;
                    double bottomRightx = (col + 1) * triangleWidth;
                    double bottomRighty = row * triangleHeight;
                    double bottomLeftx = col * triangleWidth + triangleWidth / 2;
                    double bottomLefty = row * triangleHeight + triangleHeight;
                    triangleCell.setPoints(
                            topLeftx, topLefty,
                            bottomRightx, bottomRighty,
                            bottomLeftx, bottomLefty,
                            "down"
                    );

                } else {
                    double topLeftx = col * triangleWidth + triangleWidth / 2;
                    double topLefty = row * triangleHeight;
                    double bottomRightx =(col + 1) * triangleWidth + triangleWidth / 2;
                    double bottomRighty = row * triangleHeight;
                    double bottomLeftx = col * triangleWidth + triangleWidth;
                    double bottomLefty = row * triangleHeight + triangleHeight;
                    triangleCell.setPoints(
                            topLeftx, topLefty,
                            bottomRightx, bottomRighty,
                            bottomLeftx, bottomLefty,
                            "down"
                    );
                }


                downs.add(triangleCell);
            }
            cellsDown.add(downs);
        }

        for (int row = 0; row < this.DEFAULT_HEIGHT / triangleHeight; row++) {
            ArrayList<TriangleCell> ups = new ArrayList<>();
            for (int col = 0; col < this.DEFAULT_WIDTH/ triangleWidth + 1; col++) {
                TriangleCell triangle = new TriangleCell();
                triangle.setColor(Color.WHITE);
                triangle.setStroke(Color.BLACK);

                if (row % 2 == 0) {
                    double topLeftx = col * triangleWidth;
                    double topLefty = row * triangleHeight;
                    double bottomRightx = (col - 0.5) * triangleWidth;
                    double bottomRighty = row * triangleHeight + triangleHeight;
                    double bottomLeftx = col * triangleWidth + triangleWidth / 2;
                    double bottomLefty = row * triangleHeight + triangleHeight;
                    triangle.setPoints(
                            topLeftx, topLefty,
                            bottomRightx, bottomRighty,
                            bottomLeftx, bottomLefty,
                            "up"
                    );
                } else {
                    //if(col == 0) continue;
                    double topLeftx = col * triangleWidth + triangleWidth / 2;
                    double topLefty = row * triangleHeight;
                    double bottomRightx =  (col - 0.5) * triangleWidth + triangleWidth / 2;
                    double bottomRighty = row * triangleHeight + triangleHeight;
                    double bottomLeftx = col * triangleWidth + triangleWidth;
                    double bottomLefty = row * triangleHeight + triangleHeight;
                    triangle.setPoints(
                            topLeftx, topLefty,
                            bottomRightx, bottomRighty,
                            bottomLeftx, bottomLefty,
                            "up"
                    );
                }
                ups.add(triangle);
            }
            cellsUp.add(ups);
        }
        concat(cellsUp, cellsDown);

    }

    private void concat( ArrayList<ArrayList<TriangleCell>> cellsUp, ArrayList<ArrayList<TriangleCell>> cellsDown) {
        int row = 0;
        for(int i = 0; i < cellsUp.size(); i ++) {
            ArrayList<AbstractCell> miniRes = new ArrayList<>();
            int col = 0;
            for(int j = 0; j < cellsDown.get(i).size(); j ++) {

                if (i % 2 == 0) {
                    cellsUp.get(i).get(j).setRow(row);
                    cellsUp.get(i).get(j).setColumn(col);
                    col+=1;
                    cellsDown.get(i).get(j).setRow(row);
                    cellsDown.get(i).get(j).setColumn(col);
                    col+=1;

                    miniRes.add(cellsUp.get(i).get(j));
                    miniRes.add(cellsDown.get(i).get(j));
                    if (j == cellsDown.get(i).size() - 1) {
                        cellsUp.get(i).get(j + 1).setRow(row);
                        cellsUp.get(i).get(j + 1).setColumn(col);
                        miniRes.add( cellsUp.get(i).get(j + 1));
                    }
                } else {

                    cellsDown.get(i).get(j).setRow(row);
                    cellsDown.get(i).get(j).setColumn(col);
                    col+=1;
                    cellsUp.get(i).get(j).setRow(row);
                    cellsUp.get(i).get(j).setColumn(col);
                    col+=1;

                    miniRes.add(cellsDown.get(i).get(j));
                    miniRes.add(cellsUp.get(i).get(j));
                }
            }

            if (i % 2 != 0) {miniRes.remove(miniRes.size() - 1);}
            super.cells.add(miniRes);
            row += 1;
        }
    }


}
