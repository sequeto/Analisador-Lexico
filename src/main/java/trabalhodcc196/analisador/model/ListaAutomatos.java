package trabalhodcc196.analisador.model;

import trabalhodcc196.analisador.utils.IOUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaAutomatos {
    public IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
    public List<AFD> lsAutomatos;

    public ListaAutomatos() { this.lsAutomatos = new ArrayList<>(); }

    public void imprimirLista() {
        if(lsAutomatos.isEmpty()) {
            cli.warning("Não há autômatos em memória.");

        } else {
            lsAutomatos.forEach(afd -> {
                cli.write("=========" + lsAutomatos.indexOf(afd) + "=========");
                afd.definicaoFormal();
            });
        }
    }
}
