/*
 * Trabalho Prático: DCC146 - Aspectos Teóricos da Computação
 * Autores:
 * 
 * João Pedro Sequeto Nascimento - 201776022
 * Beatriz Cunha Rodrigues - 201776038
 * Marcus Vinícius V. A. Cunha - 201776013
 * Milles Joseph M e Silva - 201776026
 */


package trabalhodcc196.analisador.main;

import java.util.Arrays;
import java.util.Scanner;


import trabalhodcc196.analisador.exceptions.InputErrorException;
import trabalhodcc196.analisador.model.*;
import trabalhodcc196.analisador.utils.IOUtils;
import trabalhodcc196.analisador.interaction.UserInteraction;

public class Main {
	
	public static IOUtils cli = new IOUtils(new Scanner(System.in), System.out);
	public static UserInteraction userInteraction = new UserInteraction();

	public static void main(String[] args) throws CloneNotSupportedException {
		String input;
		ListaTags listaTags = new ListaTags();
		// Criando Loop de Interação do Usuário
		while(true) {
			cli.write("Especifique uma TAG ou digite um comando:");
			input = cli.read();
			if(input.equals(":q")) {
				break;
			}
			else {
				try {
					UserInteraction.readInput(input, listaTags);

				} catch (InputErrorException e) {
					cli.error(String.format("Erro na entrada digitada! %s", e.getMessage() !=null ? e.getMessage() : ""));
				} catch (Exception e) {
					cli.error(e.getMessage());
				}
			}

		}

//		AFN afn = new AFN();
//
//		Estado e1 = new Estado();
//		Estado e2 = new Estado();
//		Estado e3 = new Estado();
//		Estado e4 = new Estado();
//		Estado e5 = new Estado();
//		Estado e6 = new Estado();
//		Estado e7 = new Estado();
//		Estado e8 = new Estado();
//		Estado e9 = new Estado();
//		Estado e10 = new Estado();
//
//
//		afn.adicionarEstado(e1);
//		afn.adicionarEstado(e2);
//		afn.adicionarEstado(e3);
//		afn.adicionarEstado(e4);
//		afn.adicionarEstado(e5);
//		afn.adicionarEstado(e6);
//		afn.adicionarEstado(e7);
//		afn.adicionarEstado(e8);
//		afn.adicionarEstado(e9);
//		afn.adicionarEstado(e10);
//		afn.setAlfabeto(Arrays.asList('a','o','c','r','f','h','t','l'));
//		afn.adicionarTransicao(new Transicao("c",e1,e2));
//		afn.adicionarTransicao(new Transicao("f",e1,e3));
//		//afn.adicionarTransicao(new Transicao("h",e2,e4));
//		afn.adicionarTransicao(new Transicao("o",e3,e5));
//		afn.adicionarTransicao(new Transicao("r",e5,e6));
//		afn.adicionarTransicao(new Transicao("a",e4,e5));
//		afn.adicionarTransicao(new Transicao("l",e3,e7));
//		afn.adicionarTransicao(new Transicao("o",e7,e8));
//		afn.adicionarTransicao(new Transicao("a",e8,e9));
//		afn.adicionarTransicao(new Transicao("t",e9,e10));
//
//		afn.adicionarInicial(e1);
//
//		afn.setEstadosFinais(Arrays.asList(e10));
//		afn.mostrarAutomato();
//
////		AFD afd = afn.toAFD();
////		System.out.println("======================");
//
//		//afn.removerInacessiveis();
//		//afn.removerInuteis();
//
//		afn.mostrarAutomato();
//
//		//System.out.println(afn.buscarEstado(e10,e10));



		cli.info("Fim do Programa");
	}

}
