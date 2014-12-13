package org.soen387.domain.command;


import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.command.exception.CannotChallengeSelfException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.player.IPlayer;

public class MakeMoveCommand extends CheckersCommand {
	
	public MakeMoveCommand(Helper helper) {
		super(helper);
	}
	public IPlayer player;
	public IPlayer currentPlayer;

	public void process() throws CommandException {
		try {
			if(currentPlayer==null) {
				throw new NeedToBeLoggedInException();
			}
//			if( currentGame== null ){
//				throw new NeedToCreateGameException();
//			}
//			if( currentGameIsOn == null){
//				throw new NeedToStartGame();
//			}
//			if(!player.equals(currentPlayer)){ 
//				throw new TurnException();
//			}
//			currentPlayer.makeMove();
//			currrentPlayer.equals(otherPlayer);
//			
//			if(currentGame.equals(gameEnd)){
//				players.notifyEnd("End of Game");
//			}
//			if(currentGame.equals(Tie)){
//				players.notifyEnd("Ties");
//			}
					
		}
		catch (CommandException e ) {
			throw new CommandException(e);
		}
		
	}

}
