package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.car.Car;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RacingScoreTest {
    @DisplayName("레이싱 게임 우승자 검증")
    @Test
    void findRacingGameWinner() {
        Car alpha = new Car("ALPHA");
        alpha.move(() -> true);

        Car beta = new Car("BETA");
        beta.move(() -> false);

        Car charlie = new Car("CHARLIE");
        charlie.move(() -> true);

        RacingScore racingScore = new RacingScore(Arrays.asList(alpha, beta, charlie));
        List<Car> winner = racingScore.getWinner();

        assertThat(winner).containsExactly(alpha, charlie);
    }
}