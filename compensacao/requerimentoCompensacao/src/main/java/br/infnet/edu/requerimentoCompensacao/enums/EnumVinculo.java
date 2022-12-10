package br.infnet.edu.requerimentoCompensacao.enums;


public enum EnumVinculo {
    CONJUGE(1),
    FILHO(2),
    PAI_MAE(3),
    FILHO_ADOTIVO_MENOR_GUARDA(8);

    private int codigo;

    private EnumVinculo(long code) {
        this.codigo = (int) code;
    }

    public static EnumVinculo getVinculo(long code) {
        switch ((int) code) {
            case 1:
                return CONJUGE;
            case 2:
                return FILHO;
            case 3:
                return PAI_MAE;
            case 8:
                return FILHO_ADOTIVO_MENOR_GUARDA;
            default:
                return  null;
        }
    }
 }


