package br.infnet.edu.requerimentoCompensacao.enums;

public enum EnumEstadoCivil {
    SOLTEIRO(1),
    CASADO(2),
    VIUVO(3),
    DIVORCIADO(5);

    private int codigo;

    private EnumEstadoCivil(long code) {
        this.codigo = (int) code;
    }

    public static EnumEstadoCivil getEstadoCivil(long code) {
        switch ((int) code) {
            case 1:
                return SOLTEIRO;
            case 2:
                return CASADO;
            case 3:
                return VIUVO;
            case 5:
                return DIVORCIADO;
            default:
                return  null;
        }
    }

}
