package argo;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.Term;
import jason.asSyntax.Literal;
import jason.Argo;


public class act extends DefaultInternalAction {

    private static final long serialVersionUID = -4841692752581197132L;

    private boolean isIlloc;

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {

         final Argo argoArch = Argo.getArgoArch(ts.getUserAgArch());
            if (argoArch != null) {
                Term action = args[0];
                if (argoArch.getJavino().sendCommand(argoArch.getPort(), action.toString())) {
                    return true;
                } else {
                    String PORT = argoArch.getPort();
                    String PORTshortNAME=PORT.substring(PORT.lastIndexOf("/")+1);
                    if(PORTshortNAME==""){
                        PORTshortNAME="unknown";
                    }
                    ts.getAg().getBB().remove(Literal.parseLiteral("port("+PORTshortNAME+",on);"));
                    ts.getAg().getBB().add(Literal.parseLiteral("port("+PORTshortNAME+",off);"));
                    return false;
                }
            }else{
                ts.getLogger().warning(
                        "[WARNING] It was not possible to call internal action .act because this agent is not an Argo agent.");
                return false;
            }

    }
}