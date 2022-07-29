package trabalhodcc196.analisador.model;

import java.util.Objects;

public class Estado {
    
    private String rotulo;

    private static Integer contadorRotulo = 1;

    public Estado() {
        this.rotulo = contadorRotulo.toString();
        contadorRotulo++;
    }

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
        if(obj == this){return true;}
        if (!(obj instanceof Estado)) {
            return false;
        }
        Estado e = (Estado) obj;
        return Objects.equals(e.getRotulo(),this.rotulo);
     }

}
