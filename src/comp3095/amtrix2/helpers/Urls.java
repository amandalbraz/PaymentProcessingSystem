package comp3095.amtrix2.helpers;

public final class Urls {
	private Urls() { }
	private static final String Base = "/Comp3095_Amtrix2";
	public static final String Index = Base + "/index";
	public static final String Register = Base + "/register";
	public static final String Login = Base + "/login";
	public static final String Profile = Base + "/profile";
	public static final String Credit = Base + "/credit";
	
	private static String JspBase = "/WEB-INF/pages";
	public static final String IndexJsp = JspBase + "/index.jsp";
	public static final String RegisterJsp = JspBase + "/register.jsp";
	public static final String LoginJsp = JspBase + "/login.jsp";
	public static final String ProfileJsp = JspBase + "/profile.jsp";
	public static final String CreditJsp = JspBase + "/credit.jsp";
	
}
