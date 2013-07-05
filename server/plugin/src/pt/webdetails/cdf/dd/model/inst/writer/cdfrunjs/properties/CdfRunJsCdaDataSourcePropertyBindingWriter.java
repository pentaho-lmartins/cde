
package pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.properties;

import pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.dashboard.CdfRunJsDashboardWriteContext;
import pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.properties.CdfRunJsDataSourcePropertyBindingWriter;
import pt.webdetails.cdf.dd.model.core.writer.ThingWriteException;
import pt.webdetails.cdf.dd.model.inst.DataSourceComponent;
import pt.webdetails.cdf.dd.model.inst.PropertyBinding;

/**
 * @author dcleao
 */
public final class CdfRunJsCdaDataSourcePropertyBindingWriter extends CdfRunJsDataSourcePropertyBindingWriter
{
  @Override
  public void write(StringBuilder out, CdfRunJsDashboardWriteContext context, PropertyBinding propBind) throws ThingWriteException
  {
    String dataSourceName = propBind.getValue();
    DataSourceComponent dataSourceComp = context.getDashboard().tryGetDataSource(dataSourceName);
    if(dataSourceComp != null)
    {
      String dataAccessId = dataSourceComp.tryGetPropertyValue("dataAccessId", null);

      String indent = context.getIndent();

      addJsProperty(out, "dataAccessId", buildJsStringValue(dataAccessId), indent, context.isFirstInList());

      context.setIsFirstInList(false);

      addJsProperty(out, "solution", buildJsStringValue(dataSourceComp.tryGetPropertyValue("solution", "")), indent, false);
      addJsProperty(out, "path",     buildJsStringValue(dataSourceComp.tryGetPropertyValue("path",     "")), indent, false);
      addJsProperty(out, "file",     buildJsStringValue(dataSourceComp.tryGetPropertyValue("file",     "")), indent, false);
    }
  }
}
