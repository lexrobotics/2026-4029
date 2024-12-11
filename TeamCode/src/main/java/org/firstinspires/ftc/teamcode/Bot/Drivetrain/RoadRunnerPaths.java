package org.firstinspires.ftc.teamcode.Bot.Drivetrain;

import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequenceBuilder;

public class RoadRunnerPaths {

    TrajectorySequence scoreSpec, intakeSample, toHumanPlayer, cycleSpec, toBucket, park;


    public TrajectorySequence[] redRightTrajectories = new TrajectorySequence[]{scoreSpec, intakeSample, toHumanPlayer, cycleSpec};
    public TrajectorySequence[] blueLeftTrajectories = new TrajectorySequence[]{scoreSpec, intakeSample, toHumanPlayer, cycleSpec};
    public TrajectorySequence[] redLeftTrajectories = new TrajectorySequence[]{scoreSpec, intakeSample, toBucket, park};
    public TrajectorySequence[] blueRightTrajectories = new TrajectorySequence[]{scoreSpec, intakeSample, toBucket, park};


    public void initializeRedRightTrajectories(){

    }
    public void initializeLeftRightTrajectories(){

    }
    public void initializeRedLeftTrajectories(){

    }
    public void initializeBlueLeftTrajectories(){

    }
}
