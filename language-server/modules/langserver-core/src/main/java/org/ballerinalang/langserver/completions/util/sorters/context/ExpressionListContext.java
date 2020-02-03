/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.langserver.completions.util.sorters.context;

import org.antlr.v4.runtime.ParserRuleContext;
import org.ballerinalang.langserver.commons.LSContext;
import org.ballerinalang.langserver.commons.completion.CompletionKeys;
import org.ballerinalang.langserver.commons.completion.LSCompletionItem;
import org.ballerinalang.langserver.completions.SymbolCompletionItem;
import org.ballerinalang.langserver.completions.util.Priority;
import org.ballerinalang.langserver.completions.util.sorters.CompletionItemSorter;
import org.eclipse.lsp4j.CompletionItem;
import org.wso2.ballerinalang.compiler.parser.antlr4.BallerinaParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * Item sorter for expression List context.
 *
 * @since 1.2.0
 */
public class ExpressionListContext extends CompletionItemSorter {
    @Override
    public List<CompletionItem> sortItems(LSContext ctx, List<LSCompletionItem> completionItems) {
        List<CompletionItem> cItems = new ArrayList<>();
        ParserRuleContext parserRuleContext = ctx.get(CompletionKeys.PARSER_RULE_CONTEXT_KEY);
        boolean serviceExprCtx = parserRuleContext.getParent() instanceof BallerinaParser.ServiceDefinitionContext;
        completionItems.forEach(lsCItem -> {
            CompletionItem completionItem = lsCItem.getCompletionItem();
            /*
            Captured context is as follows
            Ex: service serviceName on <cursor>
             */
            if (serviceExprCtx && lsCItem instanceof SymbolCompletionItem) {
                completionItem.setSortText(Priority.PRIORITY110.toString());
            } else {
                completionItem.setSortText(Priority.PRIORITY120.toString());
            }
            cItems.add(completionItem);
        });
        return cItems;
    }

    @Nonnull
    @Override
    protected List<Class> getAttachedContexts() {
        return Collections.singletonList(BallerinaParser.ExpressionListContext.class);
    }
}
