package MultimapMemoryLeak;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 11/6/13
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public class GuavaEx {
    private final Multimap<String, String> m_idsMM = HashMultimap.create();

    private void addPropertyGuava( final String id, final String property )
    {
        m_idsMM.put( id, property );
    }

    private boolean hasPropertyGuava( final String id, final String property )
    {
        return m_idsMM.get( id ).contains( property );
    }

    public static void main(String[] args) {
        GuavaEx guavaEx = new GuavaEx();
        guavaEx.addPropertyGuava("first", "prop");

        System.out.println(guavaEx.hasPropertyGuava("first", "prop"));

        guavaEx.addPropertyGuava("first", "prop2");

        System.out.println(guavaEx.m_idsMM);

        System.out.println(guavaEx.hasPropertyGuava("first2", "prop"));
    }
}
