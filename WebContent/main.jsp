<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<LINK href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<f:view>
	<h:outputLabel value="Hallo #{mainController.kunde.vorname }"></h:outputLabel>

	<h:form>
		<table><tr><td valign="top">
		<h:dataTable value="#{mainController.artikelgruppenliste}" var="artikelgruppe" styleClass="artikel" headerClass="artikelheader" columnClasses="first, rest">
			<h:column>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Kategorieauwahl" />
					</h:column>
				</f:facet>
				<h:commandLink value="#{artikelgruppe.bezeichnung}" action="#{mainController.filter}">
					<f:setPropertyActionListener value="#{artikelgruppe}" target="#{mainController.artikelgruppe}" />
				</h:commandLink>					
			</h:column>
		</h:dataTable>
		</td><td valign="top">
		<h:dataTable value="#{mainController.warenkorb.artikel}" var="artikel" styleClass="warenkorb" headerClass="warenkorbheader" columnClasses="firstwk, restwk">
		<h:column>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Ihr Warenkorb" />
					</h:column>
				</f:facet>
				<h:outputText value="#{artikel.bezeichnung}" />
				 &nbsp;
				<h:outputText value="#{artikel.vk_brutto} Euro" />
				<f:facet name="footer">
					<h:column>
						<h:outputText value="Gesamtpreis: #{mainController.warenkorbgesamt}"></h:outputText>
						&nbsp;|&nbsp;
						<h:commandLink value="Warenkorb leeren" action="#{mainController.emptywk}"/>
						&nbsp;|&nbsp;
						<h:commandLink value="Bestellung absenden" action="#{mainController.filter}"/>
					</h:column>
				</f:facet>
		</h:column>
		</h:dataTable>
		</td></tr></table>
	</h:form>
	<br><br>
	
		<h:dataTable value="#{mainController.artikelliste}" var="artikel" styleClass="artikel" headerClass="artikelheader" columnClasses="first, rest">
			<h:column>
				<h:graphicImage value="#{artikel.img_url}" style="width:100px;heigth:100px;" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Artikelbezeichnung" />
					</h:column>
				</f:facet>
				<h:outputText value="#{artikel.bezeichnung}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Information" />
					</h:column>
				</f:facet>
				<h:outputText value="#{artikel.info}" style="width:300px;" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Preis" />
					</h:column>
				</f:facet>
				<h:outputText value="#{artikel.vk_brutto} Euro" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:column>
						<h:outputText value="Kaufen" />
					</h:column>
				</f:facet>
				<h:form>
				<h:commandLink value="kaufen" action="#{mainController.kaufen}">
					<f:setPropertyActionListener value="#{artikel}" target="#{mainController.artikel}"/>	
				</h:commandLink>
				</h:form>
			</h:column>
		</h:dataTable>

</f:view>
</body>
</html>