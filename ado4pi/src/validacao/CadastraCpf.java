//Marcos Renan da Silva Sant Ana 3MA TADS
package validacao;
public class CadastraCpf {
	private String cpf;

	public CadastraCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	//metodo para validar o cpf
	public static boolean validarCPF(String cpf) {
		//verifica se a string esta sem caracter especiais e do tamanho correto 
		String cpfLimpo = Utilitaria.limpar(cpf);
		if (cpfLimpo.length() != 11) {
			return false;
		}

		//substring que vai ate os numeros antes do numero verificadores
		String cpfSemDigito = cpfLimpo.substring(0, 9);

		//substrings dos numeros verificadores
		String digito1 = cpfLimpo.substring(9, 10);
		String digito2 = cpfLimpo.substring(10, 11);

		//soma do primeiro digito verificador

		int soma = 0;
		int mult1 = 10;

		for(int i = 0; i < cpfSemDigito.length(); i++) {
			soma += mult1 * Integer.parseInt(cpfSemDigito.charAt(i) + "");
			mult1 --;
		}

		int resto = soma % 11;
		//utilizado metodo ternário para economizar linha e leitura de codigo
		//define o valor do primeiro digito verificador
		int cpfDigito1 = (resto < 2) ? 0 : 11 - resto;
		if (cpfDigito1 != Integer.parseInt(digito1)) {
			return false;
		}

		//define o valor do segundo digito verificador
		soma = 0;
		int mult2 = 11;

		String cpfDepoisDigito1 = cpfSemDigito + cpfDigito1;
		for(int i = 0; i < cpfDepoisDigito1.length(); i++) {
			soma += mult2 * Integer.parseInt(cpfDepoisDigito1.charAt(i) + "");
			mult2--;
		}

		int resto2 = soma % 11;
		int cpfDigito2 = (resto2 < 2) ? 0 : 11 - resto2;

		if (cpfDigito2 != Integer.parseInt(digito2)) {
			return false;
		}
		return true;
	}
}
