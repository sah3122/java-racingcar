package racingcar.domain;

import racingcar.domain.car.Car;
import racingcar.domain.car.MovingStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cars {
    private static final int INT_ZERO = 0;

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = Collections.unmodifiableList(cars);
    }

    public Cars(String[] racingCarNames) {
        validateRacingCarSize(racingCarNames);
        this.cars = carNamesToList(racingCarNames);
    }

    private List<Car> carNamesToList(String[] racingCarNames) {
        List<Car> cars = new ArrayList<>();
        for (String name : racingCarNames) {
            cars.add(new Car(name));
        }
        return cars;
    }

    private void validateRacingCarSize(String[] racingCarNames) {
        if (Objects.isNull(racingCarNames) || racingCarNames.length <= INT_ZERO) {
            throw new IllegalArgumentException("자동차의 개수는 1보다 커야 합니다.");
        }
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public List<Car> findWinners() {
        int maxPosition = calculateMaxPosition();
        return cars.stream()
                .filter(car -> car.isEqualPosition(maxPosition))
                .collect(Collectors.toList());
    }

    private int calculateMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = car.comparePosition(maxPosition);
        }
        return maxPosition;
    }

    public Cars moveAll(MovingStrategy movingStrategy) {
        List<Car> executedCars = new ArrayList<>();

        for (Car car : cars) {
            car.move(movingStrategy);
            executedCars.add(car.clone());
        }

        return new Cars(executedCars);
    }
}
