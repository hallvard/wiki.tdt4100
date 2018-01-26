<?xml version="1.0" encoding="UTF-8"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:exercise="platform:/plugin/no.hal.learning.exercise.model/model/exercise.ecore" xmlns:jdt="platform:/plugin/no.hal.learning.exercise.jdt/model/jdt-exercise.ecore" xmlns:workbench="platform:/plugin/no.hal.learning.exercise.workbench/model/workbench-exercise.ecore">
  <exercise:Exercise>
    <parts xsi:type="exercise:ExercisePart" title="Kalkulator">
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Write source code for the KalkulatorController class."/>
        <a xsi:type="jdt:JdtSourceEditAnswer" className="encapsulation.KalkulatorController"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Run the KalkulatorApp class, to test it."/>
        <a xsi:type="jdt:JdtLaunchAnswer" className="encapsulation.KalkulatorApp"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Write source code for KalkulatorApp.fxml."/>
        <a xsi:type="jdt:JdtSourceEditAnswer" resourcePath="/ovinger/src/encapsulation/KalkulatorApp.fxml"/>
      </tasks>
    </parts>
    <parts xsi:type="exercise:ExercisePart" title="Using Eclipse">
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Use breakpoints to debug code."/>
        <a xsi:type="workbench:DebugEventAnswer" action="suspend.breakpoint"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Use the debug command Step Over"/>
        <a xsi:type="workbench:CommandExecutionAnswer" elementId="org.eclipse.debug.ui.commands.StepOver" action="executeSuccess"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Use the debug command Step Into"/>
        <a xsi:type="workbench:CommandExecutionAnswer" elementId="org.eclipse.debug.ui.commands.StepInto" action="executeSuccess"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Use the Variables view"/>
        <a xsi:type="workbench:PartTaskAnswer" elementId="org.eclipse.debug.ui.VariableView" action="activated"/>
      </tasks>
    </parts>
  </exercise:Exercise>
  <exercise:ExerciseProposals exercise="/0">
    <proposals exercisePart="/0/@parts.0">
      <proposals xsi:type="jdt:JdtSourceEditProposal" question="/0/@parts.0/@tasks.0/@q" answer="/0/@parts.0/@tasks.0/@a">
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516909836995" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="5" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:StringEdit" storedString="package encapsulation;&#xA;&#xA;public class KalkulatorController {&#xA;&#xA;}&#xA;"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516909879828" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="12" errorCount="9" warningCount="1" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="import javafx.*;&#xA;&#xA;public class KalkulatorController {&#xA;&#x9;@FXML&#xA;&#x9;void handleDigitButton(ActionEvent event) {&#xA;&#x9;&#x9;// denne setningen skriver ut hvilken knapp som kalte metoden&#xA;&#x9;&#x9;System.out.println(event.getSource());&#xA;&#x9;&#x9;... her er kode for tall-knappene ...&#xA;&#x9;}" edit="/1/@proposals.0/@proposals.0/@attempts.0/@edit" start="24" end="-4"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="6" charStart="109" charEnd="127" severity="1" problemCategory="60" problemType="900"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516909888287" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="11" errorCount="2" warningCount="1" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="}" edit="/1/@proposals.0/@proposals.0/@attempts.1/@edit" start="236" end="-4"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="6" charStart="109" charEnd="127" severity="1" problemCategory="60" problemType="900"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516909891576" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="12" errorCount="8" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="event.ActionEvent;&#xA;&#xA;public class KalkulatorController {&#xA;&#x9;@FXML&#xA;&#x9;void handleDigitButton(ActionEvent event) {&#xA;&#x9;&#x9;// denne setningen skriver ut hvilken knapp som kalte metoden&#xA;&#x9;&#x9;System.out.println(event.getSource());&#xA;&#x9;&#x9;... her er kode for tall-knappene ..." edit="/1/@proposals.0/@proposals.0/@attempts.2/@edit" start="38" end="-7"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="10" charStart="273" charEnd="277" severity="2" problemCategory="50" problemType="33554515"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516909895552" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="10" errorCount="1" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="System.out.println(event.getSource());" edit="/1/@proposals.0/@proposals.0/@attempts.3/@edit" start="148" end="-7"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="6" charStart="96" charEnd="100" severity="2" problemCategory="40" problemType="16777218"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516909932210" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="13" warningCount="1" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="fxml.FXML;&#xA;import javafx.scene.control.*" edit="/1/@proposals.0/@proposals.0/@attempts.4/@edit" start="38" end="-172"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="4" charStart="56" charEnd="76" severity="1" problemCategory="120" problemType="268435844"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516909937620" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="12" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#xA;" edit="/1/@proposals.0/@proposals.0/@attempts.5/@edit" start="49" end="-169"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910175248" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="15" errorCount="7" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="void handleDigitButton(ActionEvent event) {&#xA;&#x9;&#x9;&#x9;Button button = (Button) event.getSource();&#xA;&#x9;&#x9;&#x9;valueTextField.setText(button.getText());&#xA;&#x9;&#x9;}" edit="/1/@proposals.0/@proposals.0/@attempts.6/@edit" start="174" end="-7"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="11" charStart="238" charEnd="244" severity="2" problemCategory="40" problemType="16777218"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910193912" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="16" errorCount="8" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="TextField valueTextField; // denne vil bli satt til å peke på tekstfeltet med fx:id=&quot;valueTextField&quot;" edit="/1/@proposals.0/@proposals.0/@attempts.7/@edit" start="128" end="-194"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="13" charStart="370" charEnd="384" severity="2" problemCategory="40" problemType="16777218"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910201865" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="14" errorCount="4" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="Button button = (Button) event.getSource();&#xA;&#x9;&#x9;valueTextField.setText(button.getText());" edit="/1/@proposals.0/@proposals.0/@attempts.8/@edit" start="276" end="-7"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="9" charStart="128" charEnd="137" severity="2" problemCategory="40" problemType="16777218"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910212356" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="14" errorCount="2" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="import javafx.scene.control.TextField;" edit="/1/@proposals.0/@proposals.0/@attempts.9/@edit" start="49" end="-321"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="11" charStart="331" charEnd="337" severity="2" problemCategory="40" problemType="16777218"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910220217" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="15" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="Button" edit="/1/@proposals.0/@proposals.0/@attempts.10/@edit" start="77" end="-361"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910262542" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="15" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" edit="/1/@proposals.0/@proposals.0/@attempts.11/@edit" start="443"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910273216" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="17" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#xA;&#x9;@FXML" edit="/1/@proposals.0/@proposals.0/@attempts.12/@edit" start="304" end="-142"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910409709" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="19" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="String currentText = valueTextField.getText();&#xA;&#x9;&#x9;String newText = currentText + button.getText();&#xA;&#x9;&#x9;valueTextField.setText(newText" edit="/1/@proposals.0/@proposals.0/@attempts.13/@edit" start="405" end="-9"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910466216" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="28" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="String valueText = &quot;&quot;;&#xA;&#x9; &#xA;&#x9;void appendDigit(String digit) {&#xA;&#x9;&#x9;valueText = valueText + digit;&#xA;&#x9;}&#xA;&#xA;&#x9;@FXML&#xA;&#x9;TextField valueTextField;&#xA; &#xA;&#x9;void updateValueTextField() {&#xA;&#x9;&#x9;valueTextField.setText(valueText);&#xA;&#x9;}&#xA; &#xA; @FXML&#xA;&#x9;void handleDigitButton(ActionEvent event) {&#xA;&#x9;&#x9;Button button = (Button) event.getSource();&#xA;&#x9;&#x9;appendDigit(button.getText());&#xA;&#x9;&#x9;updateValueTextField(" edit="/1/@proposals.0/@proposals.0/@attempts.14/@edit" start="195" end="-9"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910603410" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="28" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString=";" edit="/1/@proposals.0/@proposals.0/@attempts.15/@edit" start="211" end="-347"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910612895" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="28" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString=" = &quot;&quot;" edit="/1/@proposals.0/@proposals.0/@attempts.16/@edit" start="211" end="-348"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910631110" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="28" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="+=" edit="/1/@proposals.0/@proposals.0/@attempts.17/@edit" start="267" end="-284"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910639820" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="28" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString=";" edit="/1/@proposals.0/@proposals.0/@attempts.18/@edit" start="211" end="-336"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910717258" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="28" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="= valueText +" edit="/1/@proposals.0/@proposals.0/@attempts.19/@edit" start="262" end="-284"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516910757129" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="32" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#xA;&#x9;String operator;&#xA;&#x9;&#xA;&#x9;double memory;&#xA;&#x9;" edit="/1/@proposals.0/@proposals.0/@attempts.20/@edit" start="214" end="-344"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516916426235" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="32" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="dweef" edit="/1/@proposals.0/@proposals.0/@attempts.21/@edit" start="448" end="-148"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1516916427012" resourcePath="/ovinger/src/encapsulation/KalkulatorController.java" sizeMeasure="32" className="encapsulation.KalkulatorController">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="n" edit="/1/@proposals.0/@proposals.0/@attempts.22/@edit" start="448" end="-147"/>
        </attempts>
      </proposals>
      <proposals xsi:type="jdt:JdtLaunchProposal" question="/0/@parts.0/@tasks.1/@q" answer="/0/@parts.0/@tasks.1/@a"/>
      <proposals xsi:type="jdt:JdtSourceEditProposal" question="/0/@parts.0/@tasks.2/@q" answer="/0/@parts.0/@tasks.2/@a"/>
    </proposals>
    <proposals exercisePart="/0/@parts.1">
      <proposals xsi:type="workbench:DebugEventProposal" question="/0/@parts.1/@tasks.0/@q" answer="/0/@parts.1/@tasks.0/@a"/>
      <proposals xsi:type="workbench:CommandExecutionProposal" question="/0/@parts.1/@tasks.1/@q" answer="/0/@parts.1/@tasks.1/@a"/>
      <proposals xsi:type="workbench:CommandExecutionProposal" question="/0/@parts.1/@tasks.2/@q" answer="/0/@parts.1/@tasks.2/@a"/>
      <proposals xsi:type="workbench:PartTaskProposal" question="/0/@parts.1/@tasks.3/@q" answer="/0/@parts.1/@tasks.3/@a">
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1516803563502" elementId="org.eclipse.debug.ui.VariableView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1516803564618" elementId="org.eclipse.debug.ui.VariableView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1516803566084" elementId="org.eclipse.debug.ui.VariableView" action="activated"/>
      </proposals>
    </proposals>
  </exercise:ExerciseProposals>
</xmi:XMI>
