package br.edu.unicatolica.email;

public class EmailTemplate {

	private static final String LOGO = "http://unicatolicaquixada.edu.br/wp-content/themes/fcrs/template/public/images/brand-unicatolica.png";
	private static final String CONTEXT = "<CONTEXTO_APLICACAO>"; // ex.: sysfisio.fcrs.edu.br
	private static final String SISTEMA = "<NOME_APICACAO>"; // ex.: SYSFISIO

	public static String mensagem(String conteudo) {
		return pageEmail(conteudo);
	}

	private static String pageEmail(String conteudo) {
		StringBuilder page = new StringBuilder();
		page.append("<head>").append("<style>" + getCSS() + "</style>").append("</head>")
				.append("<div class=\"container100\">").append("<div class=\"container\">")
				.append("<div class=\"logo\"><img src=\"" + LOGO + "\"/><div>").append("<div class=\"card\">")
				.append("<div class=\"title\">Sistema ").append(SISTEMA).append("</div>")
				.append("<div class=\"content\">" + conteudo).append("<br/><br/>Link: ")
				.append("<a href='http://" + CONTEXT + "' target=\"_blank\">").append("Acessar ").append(SISTEMA)
				.append("</a>").append("</div>") // Fecha div content
				.append("<div class=\"footer\">").append("<p>R. Juvêncio Alves, 660 - Centro</p>")
				.append("<p>Quixadá-CE, Brasil, 63900-257</p>").append("<p>Fone: (88) 3412-6700</p>")
				.append("</div></div></div></div>");

		return page.toString();
	}

	private static String getCSS() {
		StringBuilder css = new StringBuilder();
		css.append("p { margin: 0px }");
		css.append("a { text-decoration: none; color: #4285f4; }");
		css.append("a:hover { text-decoration: underline; }");
		css.append(".container100 { padding: 15px; background: #f2f2f2; }");
		css.append(".container { width: 80%; margin: 20px auto; }");
		css.append(".logo { text-align: center }");
		css.append(".card { background: #fff; margin-top: 20px; border-top: solid 5px #fb5e00; border-radius: 3px; }");
		css.append(
				".title { color: #263238; margin: 0px 20px; font-size: 20px; border-bottom: solid 1px #ccc; padding: 15px; }");
		css.append(".content { font-size: 14px; color: #263238; text-align: left; padding: 20px; }");
		css.append(".footer { padding: 10px; font-size: 12px; color: #FFF; background: #555; }");
		css.append("@media (max-width: 640px) { .container { width: 95%; }}");

		return css.toString();
	}

}