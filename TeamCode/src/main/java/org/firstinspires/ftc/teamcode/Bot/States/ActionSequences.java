package org.firstinspires.ftc.teamcode.Bot.States;


import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Fingers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Grippers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SmartIntake;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SmartIntake;

public class ActionSequences {
    private Bot bot;
    public ActionSequences(Bot robot){
        bot = robot;
    }
    public void init(Bot robot){
        rest();
    }
    public void intake(int direction){
        switch(direction){
            case 1:
                bot.grippers.setVelocity(Grippers.INTAKE);
                break;
            case 0:
                bot.grippers.setVelocity(Grippers.STOP);
                break;
            case -1:
                bot.grippers.setVelocity(Grippers.OUTTAKE);
                break;
        }
        bot.grippers.update();
    }
//    public SmartIntake.Color smartIntake(boolean bePicky){
//        SmartIntake.Color color = SmartIntake.intake(bePicky);
//        return color;
//    }
    public SmartIntake.Color getColor(){
        double[] colors = bot.sensors.getColor(0);
        if(1.5 * colors[2] > colors[1] && colors[2] > colors[3]){
            return SmartIntake.Color.YELLOW;
        } else if(colors[2] > colors[1] && colors[2] > colors[3]){
            return SmartIntake.Color.RED;
        } else {
            return SmartIntake.Color.BLUE;
        }
    }

    public boolean getTouchingStatus(){
        boolean isTouching = bot.sensors.getTouchStatus(0);
        return isTouching;
    }

    public void specimenScoring(int level){
        switch(level){
            case 1:
                bot.outtakeSlides.setTarget(OuttakeSlides.SPC1);
                break;
            case 2:
                bot.outtakeSlides.setTarget(OuttakeSlides.SPC2);
                break;
        }
        bot.arm.setTarget(Arm.OUT);
        bot.wrist.setTarget(Wrist.SPC);
        bot.fingers.setTarget(Fingers.OUTTAKE);

    }
    public void sampleScoring(int level){
        switch(level){
            case 1:
                bot.outtakeSlides.setTarget(OuttakeSlides.BUC1);
                break;
            case 2:
                bot.outtakeSlides.setTarget(OuttakeSlides.BUC2);
                break;
        }
        bot.arm.setTarget(Arm.BUCKET);
        bot.wrist.setTarget(Wrist.BUCKET);
        bot.fingers.setTarget(Fingers.OUTTAKE);
    }
    public void intake(){
        bot.outtakeSlides.setTarget(OuttakeSlides.INTAKE);
        bot.arm.setTarget(Arm.OUT);
        bot.wrist.setTarget(Wrist.OUT);
        bot.fingers.setTarget(Fingers.INTAKE);
    }
    public void smartIntake(boolean isBlue, boolean collectNeutral){
        intake();
//        bot.grippers.smarterIntake(isBlue, collectNeutral);
    }
    public void rest(){
        bot.outtakeSlides.setTarget(OuttakeSlides.INIT);
        bot.arm.setTarget(Arm.INIT);
        bot.wrist.setTarget(Wrist.INIT);
        bot.fingers.setTarget(Fingers.INIT);
    }
    public void hang(int level){
        switch(level){
            case 0:
                bot.arm.setTarget(Arm.HANG);
                bot.wrist.setTarget(Wrist.HANG);
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }


}
