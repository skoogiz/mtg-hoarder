package in.hoarder.mtg.app;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import in.hoarder.mtg.domain.Edition;
import in.hoarder.mtg.domain.Rarity;
import in.hoarder.mtg.util.ArgUtils;

public interface Context {

	File getFile();

	List<Edition> getEditions();

	List<Rarity> getRarities();

	static Context create(String... args) {
		final String path = ArgUtils.getArgValue("--filePath", args)
				.orElseThrow(() -> new IllegalArgumentException("No file path found in args."));
		final File file = new File(path);
		if (!file.exists()) {
			throw new IllegalArgumentException("File '" + path + "' not found.");
		}
		final List<Edition> editions = ArgUtils.getArgValues("--editions", args).stream().map(Edition::of)
				.collect(Collectors.toList());
		final List<Rarity> rarities = ArgUtils.getArgValues("--rarities", args).stream().map(Rarity::of)
				.collect(Collectors.toList());
		return new Context() {
			@Override
			public File getFile() {
				return file;
			}

			@Override
			public List<Edition> getEditions() {
				return editions;
			}

			@Override
			public List<Rarity> getRarities() {
				return rarities;
			}
		};
	}
}
