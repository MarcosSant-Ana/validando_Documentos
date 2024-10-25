//Marcos Renan da Silva Sant Ana 3MA TADS
package validacao;
public class CadastraIe {
	private String ie;

	public CadastraIe(String ie) {
		this.ie = ie;
	}

	public String getIe() {
		return ie;
	}

	//metodo para validar o ie
	public static boolean validarIE(String ie) {
		//verifica se a string esta sem caracter especiais e do tamanho correto
		String ieLimpo = Utilitaria.limpar(ie);
		if(ieLimpo.length() != 9) {
			return false;
		}

		//substring que vai ate os numeros antes do numero verificadores
		String ieSemDigito = ieLimpo.substring(0, 8);

		//substring do verificador
		String digitoVerif = ieLimpo.substring(8, 9);

		int soma = 0;
		int multVerif = 9;

		for(int i = 0; i < ieSemDigito.length(); i++) {
			soma += multVerif * Integer.parseInt(ieSemDigito.charAt(i) + "");
			multVerif--;
		}
		
		int resto = soma % 11;
		int ieDigitoVerif = (resto < 2) ? 0 : 11 - resto;
		
		if(ieDigitoVerif != Integer.parseInt(digitoVerif)) {
			return false;
		}
		return true;
	}

}
