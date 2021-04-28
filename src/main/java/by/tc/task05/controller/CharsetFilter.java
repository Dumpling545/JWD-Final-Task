package by.tc.task05.controller;

import jakarta.servlet.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Filter responsible for setting http request and response encoding to
 * initParameter provided in web.xml configuration file.
 */
public class CharsetFilter implements Filter {
	private String encoding;
	private static Logger logger = LogManager.getLogger();

	private static final String CHARACTER_ENCODING = "characterEncoding";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter(CHARACTER_ENCODING);
		logger.info("Charset Filter initialized");
	}

	@Override
	public void doFilter(ServletRequest servletRequest,
	                     ServletResponse servletResponse,
	                     FilterChain filterChain)
			throws IOException, ServletException
	{
		servletRequest.setCharacterEncoding(encoding);
		servletResponse.setCharacterEncoding(encoding);
		filterChain.doFilter(servletRequest,servletResponse);
	}

	@Override
	public void destroy() {
		logger.info("Charset Filter initialized");
	}
}
