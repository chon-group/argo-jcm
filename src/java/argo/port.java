package argo;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;
import jason.Argo;

public class port extends DefaultInternalAction {

    private static final long serialVersionUID = -4841692752581197132L;

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        final Argo argoArch = Argo.getArgoArch(ts.getUserAgArch());
        if (argoArch != null) {
            Term illoc = args[0];
            String os = System.getProperty("os.name");
            if (os.substring(0, 1).equals("W")) {
                argoArch.setPort(illoc.toString());
            } else {
                argoArch.setPort("/dev/" + illoc.toString());
            }
            return true;
        }else{
            ts.getLogger().warning("[WARNING] It was not possible to call internal action .act because this agent is not an Argo agent.");
            return false;
        }
        
    }
}