package trabalhodcc196.analisador.model;

public class Estado {
    
    private String rotulo;

    private static Integer contadorRotulo = 0;

    public Estado(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

     @Override
     public boolean equals(Object obj){
             if (obj instanceof Estado) {
                 return ((Estado) obj).getRotulo().equals(this.rotulo);
             }
             return false;
     }

}
