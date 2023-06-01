package argo;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;
import jason.Argo;

public class limit extends DefaultInternalAction {

    private static final long serialVersionUID = -4841692752581197132L;

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {

        final Argo argoArch = Argo.getArgoArch(ts.getUserAgArch());
        if (argoArch != null) {
            if (args[0].isNumeric()) {
                argoArch.setLimit(Long.valueOf(args[0] + "000000"));
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