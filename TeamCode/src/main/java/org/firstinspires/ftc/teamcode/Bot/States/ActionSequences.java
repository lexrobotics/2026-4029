package org.firstinspires.ftc.teamcode.Bot.States;


import org.firstinspires.ftc.teamcode.Bot.Bot;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Fingers;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.LeftGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.RightGripper;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Slides;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.SmartIntake;
import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;

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
                bot.leftGripper.setVelocity(LeftGripper.INTAKE);
                bot.rightGripper.setVelocity(RightGripper.INTAKE);
                break;
            case 0:
                bot.leftGripper.setVelocity(LeftGripper.STOP);
                bot.rightGripper.setVelocity(RightGripper.STOP);
                break;
            case -1:
                bot.leftGripper.setVelocity(LeftGripper.EJECT);
                bot.rightGripper.setVelocity(RightGripper.EJECT);
                break;
        }
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

    public void specimenOuttake(){
        bot.slides.setTarget(bot.slides.getCurrentPosition() - 500);
        bot.leftGripper.setVelocity(LeftGripper.OUTTAKE);
        bot.rightGripper.setVelocity(RightGripper.OUTTAKE);
    }

    public void sampleOuttake(){
        bot.leftGripper.setVelocity(LeftGripper.EJECT);
        bot.rightGripper.setVelocity(RightGripper.EJECT);
    }



    public void specimenScorePrep(int level){
        switch(level){
            case 1:
                bot.slides.setTarget(Slides.SPC1);
                break;
            case 2:
                bot.slides.setTarget(Slides.SPC2);
                break;
        }
        bot.arm.setTarget(Arm.INTAKE);
        bot.wrist.setTarget(Wrist.SPECIMEN);
        bot.fingers.setTarget(Fingers.OUTTAKE);

    }
    public void sampleScorePrep(int level){
        switch(level){
            case 1:
                bot.slides.setTarget(Slides.BUC1);
                break;
            case 2:
                bot.slides.setTarget(Slides.BUC2);
                break;
        }
        bot.arm.setTarget(Arm.BUCKET);
        bot.wrist.setTarget(Wrist.BUCKET);
        bot.fingers.setTarget(Fingers.OUTTAKE);
    }
    public void intakePrep(){
        bot.slides.setTarget(Slides.INTAKE);
        bot.arm.setTarget(Arm.INTAKE);
        bot.wrist.setTarget(Wrist.INTAKE);
        bot.fingers.setTarget(Fingers.INTAKE);
    }
    public void smartIntakeSpecimen(boolean isBlue, boolean collectNeutral){
        intakePrep();
        double[] colors = bot.sensors.getColor(0);
        if(bot.sensors.getTouchStatus(0)){
            if(1.5*colors[2] > colors[1] && colors[2] > colors[3]) {
                intake(-1);
            } else if(colors[1] > colors[2] && colors[1] > colors[3]){
                if(!isBlue){
                    intake(1);
                }else{
                    intake(-1);
                }
            } else if(colors[3] > colors[1] && colors[3] > colors[2]){
                if(isBlue){
                    intake(1);
                }else{
                    intake(-1);
                }
            } else{
                intake(1);
            }
        }

//        bot.grippers.smarterIntake(isBlue, collectNeutral);
    }
    public void rest(){
        bot.slides.setTarget(Slides.INIT);
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
