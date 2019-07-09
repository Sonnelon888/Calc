package calc.enums;

import calc.operations.Addition;
import calc.operations.Division;
import calc.operations.Multiplication;
import calc.operations.Subtraction;

public enum Sign {

    ADD(1) {
        @Override
        public Double result() {
            return Addition.execute();
        }
    },
    SUB(2) {
        @Override
        public Double result() {
            return Subtraction.execute();
        }
    },
    MULT(3) {
        @Override
        public Double result() {
            return Multiplication.execute();
        }
    },
    DIV(4) {
        @Override
        public Double result() {
            return Division.execute();
        }
    };

    private Integer num;

    Sign(Integer num) {
        this.num = num;
    }

    public Integer getNumber() {
        return num;
    }

    public abstract Double result();
}
