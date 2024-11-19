package org.firstinspires.ftc.teamcode.Bot.Mechanisms;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Bot.Bot;

public class ActionSequences {
    private Bot bot;
    private double MANUAL_OUTTAKE_SLIDES_INCREMENT = 100;
    public ActionSequences(Bot robot){
        bot = robot;
    }
    public Bot getBot(){
        return bot;
    }

    public void Bucket1(){
        bot.outtakeSlides.setVelocity(1);
        bot.outtakeSlides.setTarget(OuttakeSlidesSmart.BUC1);
        bot.outtakeWrist.setTarget(OuttakeWrist.POS1);
        bot.v4b.setTarget(V4B.HOR);
    }
    public void Bucket2(){
        bot.outtakeSlides.setVelocity(1);
        bot.outtakeSlides.setTarget(OuttakeSlidesSmart.BUC2);
        bot.outtakeWrist.setTarget(OuttakeWrist.POS2);
        bot.v4b.setTarget(V4B.BUC2);
    }
    public void Specimen1(){
        bot.outtakeSlides.setVelocity(1);
        bot.outtakeSlides.setTarget(OuttakeSlidesSmart.SPC1);
        bot.outtakeWrist.setTarget(OuttakeWrist.SPC1);
        bot.v4b.setTarget(V4B.HOR);
    }
    public void GrabSpecimen(){
        bot.outtakeSlides.setVelocity(1);
        bot.outtakeSlides.setTarget(OuttakeSlidesSmart.GRA);
        bot.outtakeWrist.setTarget(OuttakeWrist.VER);
        bot.v4b.setTarget(V4B.HOR);
    }
    public void Specimen2(){
        bot.outtakeSlides.setVelocity(1);
        bot.outtakeSlides.setTarget(OuttakeSlidesSmart.SPC2);
        bot.outtakeWrist.setTarget(OuttakeWrist.SPC2);
        bot.v4b.setTarget(V4B.HOR);
    }
    public void ManualOuttakeSlides(double joystick){
        bot.outtakeSlides.setVelocity(0.5);
//        joystick = Math.signum(-joystick)*Math.pow(1,Math.abs(-joystick) * 2);
        joystick = Math.signum(-joystick)*(Math.pow(2,Math.abs(-joystick) * 2) - 1);
        bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() + MANUAL_OUTTAKE_SLIDES_INCREMENT*(joystick), 0, OuttakeSlides.MAX));
    }
    public void OuttakeRest(boolean drop){
        bot.outtakeSlides.setVelocity(1);
        bot.outtakeSlides.setTarget(OuttakeSlidesSmart.RST);
        bot.outtakeWrist.setTarget(OuttakeWrist.RST);
        bot.v4b.setTarget(V4B.RST);
        if(drop){
            bot.outtakeClaw.setTarget(OuttakeClaw.DROP);
        }
    }
    public void IntakePos1(){
        bot.intakeSlides.setTarget(IntakeSlides.POS1);
    }
    public void IntakePos2(){
        bot.intakeSlides.setTarget(IntakeSlides.POS2);
    }
    public void IntakeRest(){
        bot.intakeSlides.setTarget(IntakeSlides.RST);
    }
    public void Intake(boolean on){
        bot.intake.setVelocity(on? IntakeMotor.FORWARD_MAX: IntakeMotor.MIN_SPEED);
    }
    public void ReverseIntake(boolean on){
        bot.intake.setVelocity(on? IntakeMotor.REVERSE_MAX: IntakeMotor.MIN_SPEED);
    }
    public void ManualIntakeSlides(double joystick){
        bot.intakeSlides.setVelocity(0.5);
//        bot.intakeSlides.setTarget(Range.clip(bot.intakeSlides.getCurrentPosition() + MANUAL_OUTTAKE_SLIDES_INCREMENT*(Math.signum(-joystick)*Math.pow(1,Math.abs(-joystick) * 2)), 0, IntakeSlides.MAX));
        bot.intakeSlides.setTarget(Range.clip(bot.intakeSlides.getCurrentPosition() + MANUAL_OUTTAKE_SLIDES_INCREMENT*(Math.signum(-joystick)*(Math.pow(2,Math.abs(-joystick) * 2)) - 1), 0, IntakeSlides.MAX));
    }
    public void AttemptTransfer(Outtake OutState, Intake InState){
        if(OutState == Outtake.REST && InState == Intake.REST){
            bot.outtakeWrist.setTarget(OuttakeWrist.TRA);
            bot.outtakeClaw.setTarget(OuttakeClaw.GRIP);
        }
    }

    public void Winch1(){
        bot.winch.setTarget(Winch.H1);
    }

}
