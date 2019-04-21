/**
 * 
 */
package lc.alg.schedule;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author �
 *
 */
@Configuration
public class QuartzConfig {

	@Bean
	public JobDetail quartzDetail() {
		return JobBuilder.newJob(ScheduleSuperviseSpiderRun.class).
				withIdentity("spider_run_supervise").storeDurably().build();
	}
	
	@Bean
	public Trigger quertzTrigger() {
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInMinutes(10)		//��ʱ����10���Ӽ��
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(quartzDetail())
				.withIdentity("spider_run_supervise")
				.withSchedule(scheduleBuilder)
				.build();
	}
}
