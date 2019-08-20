package com.facdjunior.comercial.util;

import com.facdjunior.comercial.bean.AutenticacaoBean;
import com.facdjunior.comercial.domain.Usuario;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

/**
 *
 * @author Francisco Junior
 */
public class AutenticacaoListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {

        String paginaAtual = Faces.getViewId();

        boolean ehPaginaDeAutenticacao = paginaAtual.contains("Autenticacao.xhtml");

        if (!ehPaginaDeAutenticacao) {
            AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

            if (autenticacaoBean == null) {
                Faces.navigate("/pages/Autenticacao.xhtml");
                return;
            }

            Usuario usuario = autenticacaoBean.getUsuarioLogado();
            if (usuario == null) {
                Faces.navigate("/pages/Autenticacao.xhtml");
                return;
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
