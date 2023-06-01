package argo;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;
import jason.Argo;

public class percepts extends DefaultInternalAction {

    private static final long serialVersionUID = -4841692752581197132L;
    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        final Argo argoArch = Argo.getArgoArch(ts.getUserAgArch());
        if (argoArch != null) {
            if (args[0].toString().equals("block") || args[0].toString().equals("close") ) {
                argoArch.setBlocked(true);
                return true;
            }else if (args[0].toString().equals("open")) {
                argoArch.setBlocked(false);
                return true;
            } else {
                return false;
            }
        }else{
             ts.getLogger().warning("[WARNING] It was not possible to call internal action .act because this agent is not an Argo agent.");
            return false;
        }
        
    }
}