//Marcos Renan da Silva Sant Ana 3MA TADS
package validacao;
import javax.swing.JOptionPane;

public class Utilitaria {
	static String[] elementos = new String[10];
	static int tamanho = 0;
	public static void main(String[] args) {
		menu();
		if(elementos != null) {
			imprimir();
		}
	}

	public static void menu(){
		Object opcao, op;
		int resp = 0;
		do {
			String[] options = {"CNPJ", "CPF", "IE (I.Estadual)",
					"RG", "Sair"};
			opcao = JOptionPane.showOptionDialog(
					null, "Cadastre um documento:", "Verificação",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, options, options[0]);

			//Estrutura pro cnpj
			if ((int) opcao == 0) {
				String recebeCnpj = JOptionPane.showInputDialog("Digite o CNPJ", "00.111.222/0001-76");
				CadastraCnpj CNPJ = new CadastraCnpj(recebeCnpj);
				//verifica se o valor é vazio, se for vazio "cancel" ele retorna pro inicio do loop
				if (recebeCnpj == null) {
					continue;
				}
				JOptionPane.showMessageDialog(null, "CNPJ: " + CNPJ.getCnpj());
				if (Utilitaria.validarCNPJ(CNPJ)) {
					//armazena o retorno do valor no vetor
					inserirDocumento("CNPJ: " + CNPJ.getCnpj());

					JOptionPane.showMessageDialog(null, "CNPJ: " + CNPJ.getCnpj() + " válido!");
				} else {
					JOptionPane.showMessageDialog(null, "CNPJ: " + CNPJ.getCnpj() + " inválido!");
				}

				//Estrutura pro cpf
			} else if ((int) opcao == 1) {
				String recebeCpf = JOptionPane.showInputDialog("Digite o CPF", "123.456.789-01");
				CadastraCpf CPF = new CadastraCpf(recebeCpf);
				//verifica se o valor é vazio, se for vazio "cancel" ele retorna pro inicio do loop
				if (recebeCpf == null) {
					continue;
				}

				JOptionPane.showMessageDialog(null, "CPF: " + CPF.getCpf());
				if (Utilitaria.validarCPF(CPF)) {
					//armazena o retorno do valor no vetor
					inserirDocumento("CPF: " + CPF.getCpf());

					JOptionPane.showMessageDialog(null, "CPF: " + CPF.getCpf() + " válido!");
				} else {
					JOptionPane.showMessageDialog(null, "CPF: " + CPF.getCpf() + " inválido!");
				}

				//Estrutura pro ie
			} else if ((int) opcao == 2) {
				String recebeIe = JOptionPane.showInputDialog("Digite o IE","12000038-5");
				CadastraIe IE = new CadastraIe(recebeIe);
				//verifica se o valor é vazio, se for vazio "cancel" ele retorna pro inicio do loop
				if (recebeIe == null) {
					continue;
				}

				//armazena o retorno do valor no vetor
				inserirDocumento("IE: " + IE.getIe());

				JOptionPane.showMessageDialog(null, "IE: " + IE.getIe());
				if (Utilitaria.validarIE(IE)) {
					JOptionPane.showMessageDialog(null, "IE: " + IE.getIe() + " válido!");
				} else {
					JOptionPane.showMessageDialog(null, "IE: " + IE.getIe() + " inválido!");
				}

				//Estrutura para o RG, 2 opções 
			} else if ((int) opcao == 3) {
				boolean retornaMenu = false;
				do {
					String[] opCpf = {"Validar RG", "Verificar RG", "Menu"};
					op = JOptionPane.showOptionDialog(
							null, "Registo geral:", "Validação e Verificação",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
							null, opCpf, opCpf[0]);

					//Estrutura para 1° opção
					if((int) op == 0) {
						String recebeRg = JOptionPane.showInputDialog("Digite o RG sem o verificador","22.943.846-X");
						CadastraRg RG = new CadastraRg(recebeRg);
						//verifica se o valor é vazio, se for vazio "cancel" ele retorna pro inicio do loop
						if (recebeRg == null) {
							continue;
						}

						JOptionPane.showMessageDialog(null, "RG: " + RG.getRg());
						if (Utilitaria.validarRG(RG)) {
							//armazena o retorno do valor no vetor
							inserirDocumento("RG: " + RG.getRg());
							JOptionPane.showMessageDialog(null, "RG: " + RG.getRg() + " seu codigo verificador é " + RG.getResto());
						} else {
							JOptionPane.showMessageDialog(null, "RG: " + RG.getRg() + " inválido!");
						}

						//Estrutura 2° opcao
						//Estrutura pra verificar e confirmar o verificador do rg, esse metodo irá verificar se o verificador informado está matematicamente correto.
					}else if((int) op == 1) {
						String recebeRg = JOptionPane.showInputDialog("Digite o RG com o verificador","22.943.846-5");
						CadastraRg RG2 = new CadastraRg(recebeRg);
						//verifica se o valor é vazio, se for vazio "cancel" ele retorna pro inicio do loop
						if (recebeRg == null) {
							continue;
						}

						JOptionPane.showMessageDialog(null, "RG: " + RG2.getRg());
						if (Utilitaria.verificarRG(RG2)) {
							//armazena o retorno do valor no vetor
							inserirDocumento("RG: " + RG2.getRg());

							JOptionPane.showMessageDialog(null, "RG: " + RG2.getRg() + "\nO numero verificador: " + RG2.getResto2() + " está correto!");
						} else {
							JOptionPane.showMessageDialog(null, "RG: " + RG2.getRg() + " inválido!");
						}

						// Retorna ao menu
					} else if ((int) op == 2) {
						JOptionPane.showMessageDialog(null, "Retornando pro menu!");
						retornaMenu = true;
					}
				} while (!retornaMenu);

				// Estrutura para fechar a execução
			} else if ((int) opcao == 4) {
				JOptionPane.showMessageDialog(null, "Encerrando!");
				resp = JOptionPane.NO_OPTION; // Sair
			}

		} while (resp == JOptionPane.YES_OPTION);
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean validarCNPJ(CadastraCnpj cnpj) {
		String cnpjLimpo = limpar(cnpj.getCnpj());
		JOptionPane.showMessageDialog(null, "CNPJ Limpo: " + cnpjLimpo);
		return CadastraCnpj.validarCNPJ(cnpjLimpo);  // Chama o método de validação da classe
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static boolean validarCPF(CadastraCpf cpf) {
		String cpfLimpo = limpar(cpf.getCpf());
		JOptionPane.showMessageDialog(null, "CPF Limpo: " + cpfLimpo);
		return CadastraCpf.validarCPF(cpfLimpo);  // Chama o método de validação da classe
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean validarIE(CadastraIe ie) {
		String ieLimpo = limpar(ie.getIe());
		JOptionPane.showMessageDialog(null, "IE Limpo: " + ieLimpo);
		return CadastraIe.validarIE(ieLimpo);  // Chama o método de validação da classe
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static boolean validarRG(CadastraRg rg) {
		String rgLimpo = limpar(rg.getRg());
		JOptionPane.showMessageDialog(null, "RG Limpo: " + rgLimpo);
		return CadastraRg.validarRG(rgLimpo);  // Chama o método de validação da classe
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean verificarRG(CadastraRg rg) {
		String rgLimpo = limpar(rg.getRg());
		JOptionPane.showMessageDialog(null, "RG Limpo: " + rgLimpo);
		return CadastraRg.verificarRG(rgLimpo);  // Chama o método de validação da classe
	}

	//metodo para limpar os caracteres
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String limpar(String limpado) {
		String documentoLimpo = "";
		for (int i = 0; i < limpado.length(); i++) {
			if (limpado.charAt(i) == '.' || limpado.charAt(i) == '/' || limpado.charAt(i) == '-') {
				continue;
			} else {
				documentoLimpo += limpado.charAt(i);
			}
		}
		return documentoLimpo;
	}

	//metodo para adicionar o que é inserido em um vetor
	public static void inserirDocumento(String elemento) {
		if (tamanho >= elementos.length) {
			aumentaVetor();
		}
		elementos[tamanho++] = elemento;
	}

	// Método para duplicar o tamanho do vetor
	public static void aumentaVetor() {
		String[] novoVetor = new String[elementos.length * 2];
		elementos = novoVetor;
	}

	//metodo para exibir o vetor
	public static void imprimir() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tamanho; i++) {
			sb.append(elementos[i]).append("\n");
		}
			JOptionPane.showMessageDialog(null, sb.toString(), "Documentos Cadastrados", 1);
	}
}

