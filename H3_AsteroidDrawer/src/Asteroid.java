
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Darius
 */
public class Asteroid {
    private int number;
    private Polygon polygon = new Polygon();
    private static final int POINT_RADIUS = 4;
    private static final int POINT_HIT_DISTANCE = 8;
    private static final int SIDE_HIT_DISTANCE = 6;
    
    public Asteroid(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Polygon getPolygon() {
        return polygon;
    }
    
    public int getPointCount() {
        return polygon.npoints;
    }
    
    public void rotate(double angle) {
        if (polygon.npoints < 3) {
            return;
        }
        
        Point center = getSafeCenter();
        Polygon newPolygon = new Polygon();
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        
        for (int i = 0; i < polygon.npoints; i++) {
            int x = polygon.xpoints[i];
            int y = polygon.ypoints[i];
            double dx = x - center.x;
            double dy = y - center.y;
            int newX = center.x + (int) Math.round(dx * cos - dy * sin);
            int newY = center.y + (int) Math.round(dx * sin + dy * cos);
            newPolygon.addPoint(newX, newY);
        }
        polygon = newPolygon;
    }
    
    public void scale(double factor) {
        if (factor <= 0 || polygon.npoints < 3) {
            return;
        }
        
        Point center = getSafeCenter();
        Polygon newPolygon = new Polygon();
        
        for (int i = 0; i < polygon.npoints; i++) {
            int x = center.x + (int) Math.round((polygon.xpoints[i] - center.x) * factor);
            int y = center.y + (int) Math.round((polygon.ypoints[i] - center.y) * factor);
            newPolygon.addPoint(x, y);
        }
        polygon = newPolygon;
    }
    
    public void addPoint(int x, int y) {
        polygon.addPoint(x, y);
    }
    
    public void insertPoint(int index, int x, int y) {
        Polygon newPolygon = new Polygon();
        
        for (int i = 0; i < polygon.npoints; i++) {
            if (i == index) {
                newPolygon.addPoint(x, y);
            }
            newPolygon.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
        }
        
        if (index >= polygon.npoints) {
            newPolygon.addPoint(x, y);
        }
        polygon = newPolygon;
    }
    
    public void removePoint(int index) {
        if (index < 0 || index >= polygon.npoints) {
            return;
        }
        
        Polygon newPolygon = new Polygon();
        for (int i = 0; i < polygon.npoints; i++) {
            if (i != index) {
                newPolygon.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
            }
        }
        polygon = newPolygon;
    }
    
    public void movePoint(int index, int x, int y) {
        if (index < 0 || index >= polygon.npoints) {
            return;
        }
        
        polygon.xpoints[index] = x;
        polygon.ypoints[index] = y;
        polygon.invalidate();
    }
    
    public int getPointAt(Point p) {
        for (int i = 0; i < polygon.npoints; i++) {
            double distance = p.distance(polygon.xpoints[i], polygon.ypoints[i]);
            if (distance <= POINT_HIT_DISTANCE) {
                return i;
            }
        }
        return -1;
    }
    
    public int getSideAt(Point p) {
        if (polygon.npoints < 2) {
            return -1;
        }
        
        int sideCount = polygon.npoints == 2 ? 1 : polygon.npoints;
        for (int i = 0; i < sideCount; i++) {
            int next = (i + 1) % polygon.npoints;
            double distance = Line2D.ptSegDist(polygon.xpoints[i], polygon.ypoints[i],
                    polygon.xpoints[next], polygon.ypoints[next], p.x, p.y);
            if (distance <= SIDE_HIT_DISTANCE) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean inside(Point P) {
        return polygon.contains(P);
    }
    
    public Point getCentroid() {
        if (polygon.npoints == 0) {
            return new Point(0, 0);
        }
        
        if (polygon.npoints < 3) {
            return getAveragePoint();
        }
        
        double sum = 0;
        double sumX = 0;
        double sumY = 0;
        
        for (int i = 0; i < polygon.npoints; i++) {
            int next = (i + 1) % polygon.npoints;
            double xi = polygon.xpoints[i];
            double yi = polygon.ypoints[i];
            double xiNext = polygon.xpoints[next];
            double yiNext = polygon.ypoints[next];
            double cross = xi * yiNext - xiNext * yi;
            
            sum += cross;
            sumX += (xi + xiNext) * cross;
            sumY += (yi + yiNext) * cross;
        }
        
        double area = sum / 2.0;
        
        if (Math.abs(area) < 0.0001) {
            return getAveragePoint();
        }
        
        double cx = sumX / (6 * area);
        double cy = sumY / (6 * area);
        
        return new Point((int) Math.round(cx), (int) Math.round(cy));
    }
    
    private void rotatePolygon(double angleDegrees) {
        double angleRad = Math.toRadians(angleDegrees);
        Point centroid = getCenter();
        
        polygon.translate(-centroid.x, -centroid.y);
        
        for(int i = 0; i < polygon.npoints; i++) {
            int x = polygon.xpoints[i];
            int y = polygon.ypoints[i];
            
            int newX = (int) Math.round(x * Math.cos(angleRad) - y * Math.sin(angleRad));
            int newY = (int) Math.round(x * Math.sin(angleRad) + y * Math.cos(angleRad));
            
            polygon.xpoints[i] = newX;
            polygon.ypoints[i] = newY;
        }
        
        polygon.translate(centroid.x, centroid.y);
        polygon.invalidate();
    }
    
    private Point getAveragePoint() {
        int x = 0;
        int y = 0;
        
        for (int i = 0; i < polygon.npoints; i++) {
            x += polygon.xpoints[i];
            y += polygon.ypoints[i];
        }
        
        return new Point(x / polygon.npoints, y / polygon.npoints);
    }
    
    public Point getCenter() {
        int[] x = polygon.xpoints;
        int[] y = polygon.ypoints;
        int n = polygon.npoints;
        
        double area = 0;
        double cx = 0;
        double cy = 0;
        
        for(int i = 0; i < n; i++) {
            
            int j = (i + 1) % n;
            
            double factor = x[i] * y[j] - x[j] * y[i];
            area += factor;
            cx += (x[i] + x[j]) * factor;
            cy += (y[i] + y[j]) * factor;
        }
        
        area *= 0.5;
        cx /= (6 * area);
        cy /= (6 * area);
        
        return new Point((int) cx, (int) cy);
    }
    
    public void rotate(int alpha) {
        
    }
    
    private Point getSafeCenter() {
        if (polygon.npoints == 0) {
            return new Point(0, 0);
        }
        
        if (polygon.npoints < 3) {
            return getAveragePoint();
        }
        
        return getCenter();
    }
    
    public void centerIn(int width, int height) {
        if (polygon.npoints == 0) {
            return;
        }
        
        Point center = getSafeCenter();
        int dx = width / 2 - center.x;
        int dy = height / 2 - center.y;
        polygon.translate(dx, dy);
    }
    
    public boolean isSimple() {
        if (polygon.npoints < 4) {
            return true;
        }
        
        for (int i = 0; i < polygon.npoints; i++) {
            int iNext = (i + 1) % polygon.npoints;
            
            for (int j = i + 1; j < polygon.npoints; j++) {
                int jNext = (j + 1) % polygon.npoints;
                
                if (Math.abs(i - j) == 1 || (i == 0 && j == polygon.npoints - 1)) {
                    continue;
                }
                
                boolean intersect = Line2D.linesIntersect(
                        polygon.xpoints[i], polygon.ypoints[i],
                        polygon.xpoints[iNext], polygon.ypoints[iNext],
                        polygon.xpoints[j], polygon.ypoints[j],
                        polygon.xpoints[jNext], polygon.ypoints[jNext]);
                
                if (intersect) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3.0f));
        
        if(polygon.npoints > 2) {
            g2.setColor(Color.red);
            g2.drawPolygon(polygon);
        } else {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillPolygon(polygon);
            g2.setColor(Color.BLACK);
            g2.drawPolygon(polygon);
            
            g2.setColor(Color.ORANGE);
            Point centroid = getCenter();
            g2.fillOval(centroid.x - 5, centroid.y - 5, 10, 10);
        }
    }
    
    public String toFileString() {
        String line = number + ";" + polygon.npoints;
        
        for (int i = 0; i < polygon.npoints; i++) {
            line += ";" + polygon.xpoints[i] + ";" + polygon.ypoints[i];
        }
        
        return line;
    }
    
    public static Asteroid fromFileString(String line) {
        String[] items = line.split(";");
        int number = Integer.valueOf(items[0]);
        int n = Integer.valueOf(items[1]);
        Asteroid asteroid = new Asteroid(number);
        
        for (int i = 0; i < n; i++) {
            int x = Integer.valueOf(items[2 + i * 2]);
            int y = Integer.valueOf(items[3 + i * 2]);
            asteroid.addPoint(x, y);
        }
        
        return asteroid;
    }
    
    @Override
    public String toString() {
        return "Asteroid_" + number;
    }
}
