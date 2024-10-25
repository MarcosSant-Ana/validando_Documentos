//Marcos Renan da Silva Sant Ana 3MA TADS
package validacao;
public class CadastraRg {
	private String rg;
	private static int resto;
	private static int resto2;

	public CadastraRg(String rg) {
		this.rg = rg;
	}

	public String getRg() {
		return rg;
	}

	public int getResto() {
		return resto;
	}
	
	public int getResto2() {
		return resto2;
	}

	//metodo para descobrir o numero verificador do rg
	public static boolean validarRG(String rg) {
		// Verifica se a string está sem caracteres especiais
        String rgLimpo = Utilitaria.limpar(rg);

        // Verifica se o RG tem o comprimento correto sem o veficador(8 dígitos)
        if (rgLimpo.length() != 8) {
            return false;
        }

		//substring sem o numero verificador
		String rgSemDigito = rgLimpo;

		//soma dos numeros do rg
		int soma = 0;
		int multRg = 9;
		
		for(int i = rgSemDigito.length() - 1; i >= 0; i--) {
			soma += multRg * Integer.parseInt(rgSemDigito.charAt(i) + "");
			multRg--;
		}
		
		if(soma % 11 == 0) {
			resto = 0;
		}else if((soma + 100) % 11 == 0) {
			resto = 1;
		}else if((soma + 200) % 11 == 0) {
			resto = 2;
		}else if((soma + 300) % 11 == 0) {
			resto = 3;
		}else if((soma + 400) % 11 == 0) {
			resto = 4;
		}else if((soma + 500) % 11 == 0) {
			resto = 5;
		}else if((soma + 600) % 11 == 0) {
			resto = 6;
		}else if((soma + 700) % 11 == 0) {
			resto = 7;
		}else if((soma + 800) % 11 == 0) {
			resto = 8;
		}else if((soma + 900) % 11 == 0) {
			resto = 9;
		}else if((soma + 1000) % 11 == 0) {
			resto = 10;
		}else {
			return false;
		}
		return true;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//agora criei um metodo para verificar se um rg esta com o seu num verificador correto
	public static boolean verificarRG(String rg) {
		// Verifica se a string está sem caracteres especiais
        String rgLimpo = Utilitaria.limpar(rg);

        // Verifica se o RG tem o comprimento correto com o verificador(9 dígitos)
        if (rgLimpo.length() != 9) {
            return false;
        }

        // Substring sem o número verificador
        String rgSemDigito = rgLimpo.substring(0, 8);
        String digitoVerificador = rgLimpo.substring(8, 9);
        resto2 = Integer.parseInt(rgLimpo.substring(8, 9));

        // Soma dos números do RG
        int soma = 0;
        int multRg = 9;

        for (int i = rgSemDigito.length() - 1; i >= 0; i--) {
            soma += multRg * Integer.parseInt(rgSemDigito.charAt(i) + "");
            multRg--;
        }

        // Cálculo do dígito verificador
        if((soma + (Integer.parseInt(digitoVerificador) * 100)) % 11 == 0) {
        	return true;
        }
        return false;
    }
}