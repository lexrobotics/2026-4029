//package org.firstinspires.ftc.teamcode.Bot;
//
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.Bot.States.ActionSequences;
//import org.firstinspires.ftc.teamcode.Bot.States.IntakeStates;
//import org.firstinspires.ftc.teamcode.Bot.States.OuttakeStates;
//
//public class AutoSequences {
//    private int cyclenum = 0;
//    private Bot bot;
//    private ActionSequences actionSequences;
//    private ElapsedTime timer;
//
//    public int getCyclenum(){
//        return cyclenum;
//    }
//
//    public AutoSequences(Bot bot){
//        this.bot = bot;
//        actionSequences = new ActionSequences(this.bot);
//        timer = new ElapsedTime();
//    }
//        //BOTH SIDES
//     public void parkFromStart(){
//        actionSequences.OuttakeRest(true);
//        //path
//        update();
//    }
//    public void scoreSpecimen(){
//        //path to specimen
//        actionSequences.Specimen2();
//        update(0.7);
//
//    }
//    //COLOR SIDE
//    public void approachColorSample(){
//        actionSequences.OuttakeRest(true);
//        if(cyclenum == 0){
//            //first cycle path from spec scoring
//        } else if (cyclenum == 1) {
//            //path from human to sample
//        } else if (cyclenum == 2) {
//            //path from human to 3rd sample
//        }
//        cyclenum++;
//        update();
//    }
//    public void parkFromSpecimenColor(){
//        actionSequences.OuttakeRest(true);
//        //path
//        update();
//    }
//
//    public void pushToHuman(){
//        //path
//        update();
//    }
//
//    public void parkFromHuman(){
//        //path
//        update();
//    }
//
//    //YELLOW SIDE
//    public void parkFromSpecimenYellow(){
//        actionSequences.OuttakeRest(true);
//        //path
//        update();
//    }
//    public void intakeYellowSample(){
//        actionSequences.OuttakeRest(true);
//        if(cyclenum == 0){
//            //path
//        }else if(cyclenum ==1){
//            //path
//        }else if(cyclenum == 2){
//            //path
//        }
//        update();
//        actionSequences.Intake(true);
//        update();
//        cyclenum++;
//    }
//
//    public void goToBasket(){
//        actionSequences.AttemptTransfer(OuttakeStates.REST, IntakeStates.REST);
//        update();
//        if(cyclenum == 0){
//            //path
//        }else if(cyclenum ==1){
//            //path
//        }else if(cyclenum == 2){
//            //path
//        }
//        actionSequences.Bucket2();
//        update(0.7);
//
//    }
//
//    public void parkFromBasket(){
//        //path
//        update();
//    }
//
//    //GENERAL
//    private void update(double delay){
//        ElapsedTime delayTimer = new ElapsedTime();
//        delayTimer.reset();
//        while(Setup.opMode.opModeIsActive() && bot.isBusy()){
//            if(delayTimer.seconds() >= delay){
//                bot.update();
//            } else bot.drivetrain.update();
//        }
//    }
//    private void update(){
//        update(0);
//    }
//
//}
