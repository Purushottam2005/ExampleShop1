<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bestellbestätigung</title>
</head>
<body>
<f:view>
	<table>
		<tr>
			<td colspan="2"><h2><b>Bestellbestätigung</b></h2></td>
		</tr>
		<tr>
			<td><b><h:outputText value="Bestelldatum" /></b></td>
			<td><h:outputText value="#{bestellungController.bestellung.bestelldatum}"  /></td>
		</tr>
		<tr>
			<td><b><h:outputText value="Lieferadresse" /></b></td>
			<td><h:outputText value="#{bestellungController.bestellung.kunde.vorname}"/><br><h:outputText value="#{bestellungController.bestellung.kunde.adresse}"/></td>
		</tr>
		<tr>
			<td><b><h:outputText value="Bestellte Artikel" /></b></td>
			<td>
				<h:dataTable value="#{bestellungController.bestellung.artikel}" var="artikel">
					<h:column>
						<h:outputText value="#{artikel.bezeichnung}" />
									 &nbsp;
						<h:outputText value="#{artikel.vk_brutto} Euro" />
					</h:column>
				</h:dataTable>
			</td>
		</tr>
		<tr>
			<td><b><h:outputText value="Gesamtpreis" /></b></td>
			<td><h:outputText value="#{bestellungController.bestellung.gesamtpreis} Euro (incl Mwst.)"  /></td>
		</tr>
		<tr>
			<td colspan=2><b>
			<h:form>
				<h:commandLink value="Zurück zum Shop" action="#{bestellungController.toshop}"/>
			</h:form>
			</b></td>
		</tr>
	</table>
		
			






</f:view>
</body>
</html>