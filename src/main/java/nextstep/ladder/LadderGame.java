package nextstep.ladder;

import java.util.List;

import nextstep.ladder.domain.Height;
import nextstep.ladder.domain.Ladder;
import nextstep.ladder.domain.LadderBuilder;
import nextstep.ladder.domain.LadderFrame;
import nextstep.ladder.domain.Players;
import nextstep.ladder.domain.ResultOfGame;
import nextstep.ladder.domain.Results;
import nextstep.ladder.domain.line.RandomLineStrategy;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.OutputView;

public class LadderGame {
    public static final String ALL_COMMAND = "all";
    public static final String QUIT_COMMAND = "quit";

    public static void main(String[] args) {
        final List<String> names = InputView.inputNameOfPlayers();
        final List<String> resultList = InputView.inputPrizeOfResult();
        final int height = InputView.inputHeight();

        final LadderFrame ladderFrame = LadderFrame.of(Players.of(names), Results.fromString(resultList));
        final LadderBuilder ladderBuilder = LadderBuilder.of(ladderFrame, Height.of(height));
        final Ladder ladder = ladderBuilder.build(new RandomLineStrategy());

        OutputView.printLadder(ladderFrame, ladder);

        ResultOfGame resultOfGame = ladder.result(ladderFrame);

        String nameOfUser = "";
        while(!nameOfUser.equalsIgnoreCase(QUIT_COMMAND)) {
            nameOfUser = InputView.inputNameForResult();
            if (nameOfUser.equalsIgnoreCase(QUIT_COMMAND)) {
                break;
            }

            OutputView.printResultOfPlayers(nameOfUser, resultOfGame);
        }
    }
}
