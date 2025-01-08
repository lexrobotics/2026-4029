//package org.firstinspires.ftc.teamcode.Bot.States;
//
//import com.qualcomm.robotcore.util.Range;
//
//import org.firstinspires.ftc.teamcode.Bot.Bot;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Arm;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.IntakeSlides;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Noodler;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeClaw;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.OuttakeSlides;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Wrist;
//import org.firstinspires.ftc.teamcode.Bot.Mechanisms.Winch;
//
//public class ActionSequences {
//    private Bot bot;
//    private double MANUAL_OUTTAKE_SLIDES_INCREMENT = 100;
//    public ActionSequences(Bot robot){
//        bot = robot;
//    }
//    public Bot getBot(){
//        return bot;
//    }
//
//    public void IntakeMotor(int vel, boolean drop){
//        bot.noodler.setVelocity(vel);
//        if(drop) bot.arm.setTarget(Arm.INTAKE);
//    }
//    public void V4BAngled(){
//        bot.v4b.setTarget(V4B.ANG);
//    }
//    public void V4BRest(){
//        bot.v4b.setTarget(V4B.TRANSFER);
//    }
//
//    public void Bucket1(){
//        bot.outtakeSlides.setVelocity(1);
//        bot.outtakeSlides.setTarget(OuttakeSlides.BUC1);
//        bot.outtakeWrist.setTarget(Wrist.POS1);
//        bot.v4b.setTarget(V4B.ANG);
//        bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
//    }
//    public void Bucket2(){
//        bot.outtakeSlides.setVelocity(1);
//        bot.outtakeSlides.setTarget(OuttakeSlides.BUC2);
//        bot.outtakeWrist.setTarget(Wrist.POS2);
//        bot.v4b.setTarget(V4B.ANG);
//        bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
//
//    }
//    public void Specimen1(){
//        bot.outtakeSlides.setVelocity(1);
//        bot.outtakeSlides.setTarget(OuttakeSlides.SPC1);
//        bot.outtakeWrist.setTarget(Wrist.SPC1);
//        bot.v4b.setTarget(V4B.ANG);
//        bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
//
//    }
//    public void GrabSpecimen(){
//        bot.outtakeSlides.setVelocity(1);
//        bot.outtakeSlides.setTarget(OuttakeSlides.GRA);
//        bot.outtakeWrist.setTarget(Wrist.VER);
//        bot.v4b.setTarget(V4B.HOR);
//    }
//    public void Specimen2(){
//        bot.outtakeSlides.setVelocity(1);
//        bot.outtakeSlides.setTarget(OuttakeSlides.SPC2);
//        bot.outtakeWrist.setTarget(Wrist.SPC2);
//        bot.v4b.setTarget(V4B.ANG);
//        bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
//    }
//    public void ManualOuttakeSlides(double joystick){
//        bot.outtakeSlides.setVelocity(0.5);
////        joystick = Math.signum(-joystick)*Math.pow(1,Math.abs(-joystick) * 2);
////        joystick = Math.signum(-joystick)*(Math.pow(2,Math.abs(-joystick) * 2) - 1);
////        bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() + MANUAL_OUTTAKE_SLIDES_INCREMENT*(joystick), 0, OuttakeSlides.MAX));
//        bot.outtakeSlides.setTarget(Range.clip(bot.outtakeSlides.getCurrentPosition() + (MANUAL_OUTTAKE_SLIDES_INCREMENT/2)*(Math.signum(-joystick)*(Math.pow(2,Math.abs(-joystick) * 2)) - 1), 0, OuttakeSlides.MAX));
//    }
//    public void OuttakeRest(boolean drop){
//        bot.outtakeSlides.setVelocity(0.5);
//        bot.outtakeSlides.setTarget(OuttakeSlides.RST);
//        bot.outtakeWrist.setTarget(Wrist.TRA);
//        bot.v4b.setTarget(V4B.TRANSFER);
//        if(drop){
//            bot.outtakeClaw.setTarget(OuttakeClaw.DROP);
//        }
//    }
//    public void IntakePos1(){
//        bot.intakeSlides.setTarget(IntakeSlides.POS1);
////        bot.intakeArm.setTarget(IntakeArm.INTAKE);
//    }
//    public void IntakePos2(){
//        bot.intakeSlides.setTarget(IntakeSlides.POS2);
////        bot.intakeArm.setTarget(IntakeArm.INTAKE);
//    }
//    public void IntakeRest(){
//        bot.intakeSlides.setTarget(IntakeSlides.RST);
//        bot.intakeArm.setTarget(Arm.TRANSFER);
////        bot.noodler.setVelocity(0.3);
//    }
//    public void Intake(boolean on){
//        bot.noodler.setVelocity(on? Noodler.FORWARD_MAX: Noodler.MIN_SPEED);
//        bot.intakeArm.setTarget(Arm.INTAKE);
//    }
//    public void ReverseIntake(boolean on){
//        bot.noodler.setVelocity(on? Noodler.REVERSE_MAX: Noodler.MIN_SPEED);
//    }
//    public void ManualIntakeSlides(double joystick){
//        bot.intakeSlides.setVelocity(0.5);
//
////        bot.intakeSlides.setTarget(Range.clip(bot.intakeSlides.getCurrentPosition() + MANUAL_OUTTAKE_SLIDES_INCREMENT*(Math.signum(-joystick)*Math.pow(1,Math.abs(-joystick) * 2)), 0, IntakeSlides.MAX));
//        bot.intakeSlides.setTarget(Range.clip(bot.intakeSlides.getCurrentPosition() + MANUAL_OUTTAKE_SLIDES_INCREMENT*(Math.signum(-joystick)*(Math.pow(1.1,Math.abs(-joystick) * 2)) - 1), 0, IntakeSlides.MAX));
//    }
//    public void AttemptTransfer(OuttakeStates OutState, IntakeStates InState){
//        if(OutState == OuttakeStates.REST && InState == IntakeStates.REST){
//            bot.intakeSlides.setTarget(IntakeSlides.TRANSFER);
//            bot.v4b.setTarget(V4B.TRANSFER);
//            bot.outtakeWrist.setTarget(Wrist.TRA);
//            bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
//            bot.intakeArm.setTarget(Arm.TRANSFER);
//        }
//    }
//    public void PrepScore(){
//        bot.outtakeClaw.setTarget(OuttakeClaw.INIT);
//        bot.v4b.setTarget(V4B.TRANSFER);
//        bot.outtakeWrist.setTarget(Wrist.TRA);
//    }
//
//    public void Winch1(){
//        bot.winch.setTarget(Winch.H1);
//    }
//
//}
