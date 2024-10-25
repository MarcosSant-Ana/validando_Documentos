//Marcos Renan da Silva Sant Ana 3MA TADS
package validacao;
import javax.swing.JOptionPane;

public class CadastraCnpj {
	private String cnpj;

	public CadastraCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public static boolean validarCNPJ(String cnpj) {
		//verifica se a string esta sem caracter especiais e do tamanho correto
		String cnpjLimpo = Utilitaria.limpar(cnpj);

		if (cnpjLimpo.length() != 14) {
			return false;
		}

		//substring que vai ate os numeros antes do numero verificadores
		String cnpjSemDigito = cnpjLimpo.substring(0, 12);

		//substrings dos numeros verificadores
		String digito1 = cnpjLimpo.substring(12, 13);
		String digito2 = cnpjLimpo.substring(13, 14);

		//soma de todos os numeros e descobrindo o verificador 1
		int soma = 0;
		int[] mult1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

		for (int i = 0; i < cnpjSemDigito.length(); i++) {
			soma += mult1[i] * Integer.parseInt(cnpjSemDigito.charAt(i) + "");
		}

		int resto = soma % 11;
		int cnpjDigito1;
		//define o valor do primeiro digito verificador
		if (resto < 2) {
			cnpjDigito1 = 0;
		} else {
			cnpjDigito1 = 11 - resto;
		}

		if (cnpjDigito1 != Integer.parseInt(digito1)) {
			return false;
		}

		//soma pro segundo digito verificador
		soma = 0;
		String cnpjDepoisDigito1 = cnpjSemDigito + cnpjDigito1;
		int[] mult2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

		for (int i = 0; i < cnpjDepoisDigito1.length(); i++) {
			soma += mult2[i] * Integer.parseInt(cnpjDepoisDigito1.charAt(i) + "");
		}

		int resto2 = soma % 11;
		int cnpjDigito2;

		//define o valor do segundo digito verificador
		if (resto2 < 2) {
			cnpjDigito2 = 0;
		} else {
			cnpjDigito2 = 11 - resto2;
		}

		if (cnpjDigito2 != Integer.parseInt(digito2)) {
			return false;
		}
		return true;
	}
}