package pers.hsc.evats.core.tags.html;

/**
 * @author hsc
 *
 * Mar 29, 2018
 */
@SuppressWarnings("serial")
public class CssComponentTag extends AbstractHtmlTag {
	private static final String[] SUPPORT_TYPES = { "CSS" };

	@Override
	public String[] getSupportTypes() {
		return SUPPORT_TYPES;
	}

}
