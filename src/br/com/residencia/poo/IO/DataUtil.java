package br.com.residencia.poo.IO;
	
	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class DataUtil {

		public static String converterDateParaDataEHora(Date data) {
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
			return formatador.format(data);
		}
		
		public static String converterDateParaData(Date data) {
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/YYYY");
			return formatador.format(data);
		}
}