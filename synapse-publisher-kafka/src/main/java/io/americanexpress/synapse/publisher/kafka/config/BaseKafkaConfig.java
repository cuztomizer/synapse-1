package io.americanexpress.synapse.publisher.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * <code>BaseKafkaConfig</code> class is contains the bean for KafkaTemplate used to connect to a kafka topic.
 * <p>
 * The implementation of this class will be the main configuration that needs to be imported to other modules.
 *
 * @author Krishna Kuchikulla
 */

@EnableKafka
public abstract class BaseKafkaConfig<T extends BaseKafkaProperties.BaseKafkaTemplate, P extends BaseKafkaProperties.BaseKafkaProducer<S>, S extends BaseKafkaProperties.BaseKafkaSsl, K extends BaseKafkaProperties<T, P, S>> {

	/**
	 * KafkaPropertiesConfig.
	 */
	private final K kafkaPropertiesConfig;

	/**
	 * BaseKafkaConfig constructor
	 *
	 * @param kafkaPropertiesConfig kafka properties
	 */
	protected BaseKafkaConfig(K kafkaPropertiesConfig) {
		this.kafkaPropertiesConfig = kafkaPropertiesConfig;
	}

	/**
	 * This method returns kafkaTemplate bean which is used to create the BaseKafkaPublisher
	 *
	 * @return kafkaTemplate
	 */
	@Bean
	public KafkaTemplate<String, String> getKafkaTemplate() {
		DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(this.kafkaPropertiesConfig.buildProducerProperties());
		KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory);
		kafkaTemplate.setDefaultTopic(kafkaPropertiesConfig.getTemplate().getDefaultTopic());
		return kafkaTemplate;
	}
}
