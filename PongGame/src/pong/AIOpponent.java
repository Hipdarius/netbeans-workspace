package pong;

public class AIOpponent {
    private final int screenHeight;
    private final int paddleHeight;
    private final int maxSpeed;

    private int targetY;

    public AIOpponent(int screenHeight, int paddleHeight, int maxSpeed) {
        this.screenHeight = screenHeight;
        this.paddleHeight = paddleHeight;
        this.maxSpeed = maxSpeed;
        this.targetY = 0;
    }

    public int update(int currentY, int ballX, int ballY, int ballVX, int screenWidth) {
        boolean ballComing = ballVX > 0 && ballX > screenWidth / 3;
        if (ballComing) {
            targetY = ballY - paddleHeight / 2;
        } else {
            targetY = (screenHeight - paddleHeight) / 2;
        }

        if (currentY < targetY) {
            currentY += Math.min(maxSpeed, targetY - currentY);
        } else if (currentY > targetY) {
            currentY -= Math.min(maxSpeed, currentY - targetY);
        }

        if (currentY < 0) currentY = 0;
        if (currentY > screenHeight - paddleHeight) currentY = screenHeight - paddleHeight;
        return currentY;
    }
}
