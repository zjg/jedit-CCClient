
package ccclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.gjt.sp.jedit.Mode;
import org.gjt.sp.jedit.View;
import org.gjt.sp.jedit.jEdit;
import org.gjt.sp.jedit.textarea.TextArea;
 
import completion.service.CompletionCandidate;
import completion.service.CompletionProvider;
import completion.util.CompletionUtil;
import completion.util.BaseCompletionCandidate;

import ccclient.CCClientPlugin;

public class CodeCompletion implements CompletionProvider
{
   private Set<Mode> completionModes;
   
   public CodeCompletion()
   {
      super();
      completionModes = new HashSet<Mode>();
      completionModes.add(jEdit.getMode("c"));
      completionModes.add(jEdit.getMode("c++"));
   }
   
   /**
   * @param view
   * @return The list of possible completions based on the current caret location.
   */
   @Override
   public List<CompletionCandidate> getCompletionCandidates(View view)
   {
      String file = view.getBuffer().getPath();
      TextArea ta = view.getTextArea();
      int line = ta.getCaretLine();
      int column = ta.getCaretPosition() - ta.getLineStartOffset(line);
      
      // move backwards by the completion prefix, since clang
      // is expecting the line/column to be right after the
      // start token (not within a word)
      column -= CompletionUtil.getCompletionPrefix(view).length();
      
      
      
      
      // todo: get completion results
      
      
      
      // libclang.CXCompletionResult[] clangResults =
      //    clangCompletions.Results.toArray(clangCompletions.NumResults);
      
      // Map<Integer, List<CompletionCandidate>> prioMap =
      //    new HashMap<Integer, List<CompletionCandidate>>();
      
      // for (libclang.CXCompletionResult result : clangResults)
      // {
      //    CXString.ByValue chunkText =
      //       LibclangLibrary.INSTANCE.clang_getCompletionChunkText(
      //          result.CompletionString,
      //          LibclangLibrary.CXCompletionChunkKind.CXCompletionChunk_TypedText);
         
      //    if (chunkText != null)
      //    {
      //       String desc = LibclangLibrary.INSTANCE.clang_getCString(chunkText);
      //       // I don't think I need to dispose the chunkText CXString here...
      //       // ... based on looking through the libclang sources for getCompletionChunkText()
            
      //       if (desc != null)
      //       {
      //          // add code completion based on its priority
      //          int prio = LibclangLibrary.INSTANCE.clang_getCompletionPriority(result.CompletionString);
      //          List<CompletionCandidate> list = prioMap.get(prio);
      //          if (list == null)
      //          {
      //             prioMap.put(prio, list = new ArrayList<CompletionCandidate>());
      //          }
      //          list.add(new BaseCompletionCandidate(desc));
      //       }
      //    }
      // }
      // // Disposing of the completion results was causing crashes....
      // // LibclangLibrary.INSTANCE.clang_disposeCodeCompleteResults(clangCompletions);
      
      List<CompletionCandidate> codeCompletions = new ArrayList<CompletionCandidate>();
      
      // Integer[] prioList = prioMap.keySet().toArray(new Integer[1]);
      // Arrays.sort(prioList);
      // // for (int i = prioList.length - 1; i >= 0; --i)
      // for (int i = 0; i < prioList.length; ++i)
      // {
      //    codeCompletions.addAll(prioMap.get(prioList[i]));
      // }
      
      return codeCompletions;
   }
   
   /**
   * @return A list of supported modes (usually only one if any), or null if not mode specific.
   */
   @Override
   public Set<Mode> restrictToModes()
   {
      return completionModes;
   }
   
}
