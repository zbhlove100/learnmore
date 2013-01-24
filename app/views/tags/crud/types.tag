%{
    models = [];
    for(controllerClass in play.Play.classloader.getAssignableClasses(_('controllers.CRUD'))) {
        resourceModel = _('controllers.CRUD$ObjectType').get(controllerClass)
        if(resourceModel != null) {
            models.add(resourceModel.controllerClass.name.substring(12).replace('$',''))
        }
    }
    for(controllerClass in play.Play.classloader.getAssignableClasses(_('play.scalasupport.crud.CRUDWrapper'))) {
        resourceModel = _('controllers.CRUD$ObjectType').get(controllerClass)
        if(resourceModel != null) {
            models.add(resourceModel)
        }
    }
    java.util.Collections.sort(models)
}%

%{ models.eachWithIndex() { item, i -> }%
	%{
		attrs = [:]
		attrs.put('type', item)
	}%
    #{doBody vars:attrs /}
%{ } }%