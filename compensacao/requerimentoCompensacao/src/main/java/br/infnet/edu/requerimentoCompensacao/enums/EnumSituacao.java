package br.infnet.edu.requerimentoCompensacao.enums;

public enum EnumSituacao {
    DEFERIDO(1),
    INDEFERIDO(2),
    CANCELADO(3),
    ANALISE(4);

    private int codigo;

    private EnumSituacao(long code) {
        this.codigo = (int) code;
    }

    public static EnumSituacao getSituacao(long code) {
        switch ((int) code) {
            case 1:
                return DEFERIDO;
            case 2:
                return  INDEFERIDO;
            case 3:
                return CANCELADO;
            case 4:
                return ANALISE;
            default:
                return  null;
        }
    }

}
