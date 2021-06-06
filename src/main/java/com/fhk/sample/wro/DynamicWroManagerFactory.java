package com.fhk.sample.wro;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import org.springframework.core.io.ClassPathResource;

import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerOptions.LanguageMode;

import ro.isdc.wro.extensions.processor.js.GoogleClosureCompressorProcessor;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.SimpleProcessorsFactory;
import ro.isdc.wro.model.resource.processor.impl.css.CssImportPreProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssVariablesProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.JawrCssMinifierProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.SemicolonAppenderPreProcessor;

class DynamicWroManagerFactory extends BaseWroManagerFactory  
{
	@Inject
	private WebUriLocatorFactory webUriLocatorFactory;
	
	@Override
	protected UriLocatorFactory newUriLocatorFactory() 
	{
		return webUriLocatorFactory;
	}
	
	@Override
	protected ProcessorsFactory newProcessorsFactory() 
	{
	    final SimpleProcessorsFactory factory = new SimpleProcessorsFactory();
	    factory.addPreProcessor(new CssImportPreProcessor());
	    factory.addPreProcessor(new SemicolonAppenderPreProcessor());
	    
	    final GoogleClosureCompressorProcessor gccp = new GoogleClosureCompressorProcessor(CompilationLevel.SIMPLE_OPTIMIZATIONS)
	    {
	    	@Override
			protected CompilerOptions newCompilerOptions()
	    	{
	    		final CompilerOptions co = super.newCompilerOptions();
	    		co.setLanguageIn(LanguageMode.ECMASCRIPT5);
	    		return co;
	    	}
	    };
	    factory.addPreProcessor(gccp);
	    factory.addPreProcessor(new JawrCssMinifierProcessor());
	    factory.addPostProcessor(new CssVariablesProcessor());
	    return factory;
	}
	
	@Override
    protected WroModelFactory newModelFactory() 
	{
        return new XmlModelFactory() 
        {
            @Override
            protected InputStream getModelResourceAsStream() throws IOException 
            {
                return new ClassPathResource(getDefaultModelFilename()).getInputStream();
            }
        };
    }
}
