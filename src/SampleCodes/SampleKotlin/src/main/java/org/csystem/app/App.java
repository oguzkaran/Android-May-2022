/*----------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import org.csystem.util.thread.ThreadUtil;

class App {
    public static void main(String[] args)
    {
        System.out.println("main starts");
        ThreadUtil.sleep(3000);
        System.out.println("main ends");
    }
}

