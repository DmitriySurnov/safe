import dataModel.Assumptions;
import dataModel.TurnResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bot {
    private List<Assumptions> _numbers;
    private int _countCorrectDigits;
    private int _number;
    private Number[] _currentTrurn;
    private Number[] _trurn;
    private int _figure;

    Bot() {
        _numbers = new ArrayList<>();
        _countCorrectDigits = 0;
        _currentTrurn = new Number[]{-1, -1, -1, -1};
        _figure = -1;
        _number = -1;
    }

    public void InitGameStart() {

    }

    public String getTurn() {

        _trurn = _currentTrurn.clone();

        if (_numbers.size() + _countCorrectDigits != 4) {
            _figure++;

            for (int j = 0; j < 4; j++) {

                if (_trurn[j].intValue() == -1) {
                    _trurn[j] = _figure;
                }
            }
        } else {
            if (_number == -1)
                _number = ++_figure;

            for (int g = 0; g < _numbers.getFirst().position.length; g++) {
                int index = _numbers.getFirst().position[g].intValue();
                if (index != -1 && _trurn[index].intValue() == -1) {
                    _trurn[index] = _numbers.getFirst().number;
                    break;
                }
            }

            for (int j = 0; j < 4; j++) {

                if (_trurn[j].intValue() == -1) {
                    _trurn[j] = _number;
                }
            }
        }

        return Utilites.TurnArrayToString(_trurn);
    }

    public void setTurnResult(TurnResult result) {
        if (result.DigitsInPlase == 4 && result.CorrectDigits == 4)
            return;
        if (result.DigitsInPlase == 0 && result.CorrectDigits == 0) {
            if (_number == -1)
                _number = _figure;
        } else if (result.DigitsInPlase == 1 && result.CorrectDigits == 4) {
            _numbers.add(new Assumptions(_figure));
        } else if (result.DigitsInPlase == _countCorrectDigits && result.CorrectDigits == _countCorrectDigits + 1) {
            int index = Arrays.asList(_trurn).indexOf(_numbers.getFirst().number);
            _numbers.getFirst().delitIndex(index);
        }
        else {
            int index = Arrays.asList(_trurn).indexOf(_numbers.getFirst().number);
            _currentTrurn[index] = _numbers.getFirst().number;
            _numbers.removeFirst();
            _countCorrectDigits++;
        }
    }
}
