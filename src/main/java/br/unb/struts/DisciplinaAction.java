package br.unb.struts;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.unb.dao.DisciplinaDAO;
import br.unb.dominio.Disciplina;

public class DisciplinaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("DisciplinaAction::execute");
		DisciplinaForm disciplinaForm = (DisciplinaForm) form;
		DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
//     // Realize a validação do disciplinaForm antes de salvar
//        ActionMessages errors = new ActionErrors();
//        if (disciplinaForm.getNome() == null || disciplinaForm.getNome().trim().isEmpty()) {
//            errors.add("nome", new ActionMessage("error.nome.required"));
//        }
//        if (disciplinaForm.getMatricula() == null || disciplinaForm.getMatricula().trim().isEmpty()) {
//            errors.add("matricula", new ActionMessage("error.matricula.required"));
//        }
//
//        if (!errors.isEmpty()) {
//            // Se houver erros, adicione-os ao request e retorne a página de entrada (input)
//            saveErrors(request, errors);
////            return mapping.findForward("success");
//        }
		String method = request.getParameter("method");
		System.out.println("DisciplinaAction::execute::method " + method);
		if (method != null && !method.isEmpty()) {
			switch (method) {
			case "salvar":
				Disciplina disciplina = new Disciplina(disciplinaForm.getNome(), disciplinaForm.getTurma(),
						disciplinaForm.getCurso(), disciplinaForm.getLocal());
				disciplinaDAO.salvar(disciplina);
				break;
			case "editar":
				int id = Integer.parseInt(request.getParameter("id"));
				disciplina = disciplinaDAO.getById(Long.valueOf(id));
				disciplinaForm.setId(disciplina.getId());
				disciplinaForm.setNome(disciplina.getNome());
				disciplinaForm.setTurma(disciplina.getTurma());
				disciplinaForm.setCurso(disciplina.getCurso());
				disciplinaForm.setLocal(disciplina.getLocal());
				break;
			case "atualizar":
				disciplina = new Disciplina(disciplinaForm.getNome(), disciplinaForm.getTurma(),
						disciplinaForm.getCurso(), disciplinaForm.getLocal());
				disciplinaDAO.update(disciplina);
				break;
			case "excluir":
				id = Integer.parseInt(request.getParameter("id"));
				disciplinaDAO.delete(Long.valueOf(id));
				;
				break;
			}
		}

		List<Disciplina> disciplinas = disciplinaDAO.findAll();
		request.setAttribute("disciplinas", disciplinas);

		return mapping.findForward("success");
	}
}
